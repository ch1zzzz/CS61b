package bearmaps;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private ArrayList<PriorityNode> items;
    private HashMap<T, Integer> indexMap;

    public ArrayHeapMinPQ() {
        items = new ArrayList<>();
        indexMap = new HashMap<>();
        items.add(null);
    }

    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentExceptionb if item is already present.
     * You may assume that item is never null. */
    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException();
        }
        items.add(new PriorityNode(item, priority));
        indexMap.put(item, size());
        swim(size());
    }

    //node go up
    private void swim(int k) {
        if (k == 1) {
            return;
        }
        if (items.get(parent(k)).getPriority() > items.get(k).getPriority()) {
            swap(k, parent(k));
            swim(parent(k));
        }
    }

    //change 2 nodes
    private void swap(int k, int p) {
        PriorityNode tmp = items.get(k);
        indexMap.put(items.get(p).getItem(), k);
        indexMap.put(tmp.getItem(), p);
        items.set(k, items.get(p));
        items.set(p, tmp);
    }
    /* Returns true if the PQ contains the given item. */
    @Override
    public boolean contains(T item) {
        return indexMap.containsKey(item);
    }

    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T getSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        return items.get(1).getItem();
    }

    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T removeSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }

        swap(1, size());
        T small = items.remove(size()).getItem();
        indexMap.remove(small);
        sink(1);
        return small;
    }

    // node go down
    private void sink(int k) {
        while (leftChild(k) <= size()) {
            int leftc = leftChild(k);
            int rightc = rightChild(k);
            int min;

            if (rightc > size()) {
                min = leftc;
            } else {
                min = items.get(leftc).getPriority() < items.get(rightc).getPriority() ? leftc : rightc;
            }

            if (items.get(k).getPriority() < items.get(leftc).getPriority()) {
                break;
            } else {
                swap(k, min);
                k = min;
            }
        }
    }

    /* Returns the number of items in the PQ. */
    @Override
    public int size() {
        return items.size() - 1;
    }

    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException();
        }
        int index = indexMap.get(item);
        double oldPriority = items.get(index).getPriority();
        items.get(index).setPriority(priority);
        if (oldPriority > priority) {
            swim(index);
        } else {
            sink(index);
        }
    }

    private int leftChild(int k) {return 2 * k;}
    private int rightChild(int k) {return 2 * k + 1;}
    private int parent(int k) {return k / 2;}

    private class PriorityNode {
        private T item;
        private double priority;

        PriorityNode(T e, double p) {
            this.item = e;
            this.priority = p;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }
    }

}

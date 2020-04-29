/**
 * ArrayDeque
 * implemented in circular way
 * @author Ch1zzZ
 */

public class ArrayDeque<Item> {
    private Item[] items;
    private int first;
    private int last;
    private int size;

    /** Create an empty list. */
    public ArrayDeque(){
        items = (Item[]) new Object[8];
        first = 0;
        last = 1;
        size = 0;
    }

    /** Return the size of the ArrayDeque. */
    public int size(){
        return size;
    }

    /** Returns true if deque is empty. */
    public boolean isEmpty(){
        return size == 0;
    }

    /** return true if deque is full. */
    public boolean isFull(){
        return size == items.length;
    }

    /** return x - 1 circularly. */
    public int minusOne(int x){
        return (x - 1 + items.length) % items.length;
    }

    /** return x + 1 circularly. */
    public int plusOne(int x){
        return (x + 1 + items.length) % items.length;
    }

    /** Resize the deque. */
    public void resize(int capacity){
        Item[] newItems =  (Item[]) new Object[capacity];
        int start = plusOne(first);
        for (int i = 0;i<size;i++){
            newItems[i] = items[start];
            start = plusOne(start);
        }
        items = newItems;
        first = capacity - 1;
        last = size;
    }

    /** Return true if the deque is sparse. */
    public boolean isSparse(){
        return items.length>16 && (size/items.length)<0.25;
    }

    /** Adds an item to the front of the deque. */
    public void addFirst(Item x){
        if (size == items.length){
            this.resize(2 * size);
        }
        items[first] = x;
        first = minusOne(first);
        size += 1;
    }

    /** Adds an item to the front of the deque. */
    public void addLast(Item x){
        if (size == items.length){
            this.resize(2 * size);
        }
        items[last] = x;
        last = plusOne(last);
        size += 1;
    }

    /** Prints the items in the deque from first to last. */
    public void printDeque(){
        int start = plusOne(first);
        for (int i = 0; i<size; i++){
            System.out.print(items[start] + " ");
            start = plusOne(start);
        }
    }

    /** Removes and returns the item at the front of the deque. */
    public Item removeFirst(){
        if (this.isEmpty()){
            return null;
        } else {
            if (this.isSparse()){
                this.resize(size/2);
            }
            first = plusOne(first);
            Item x = items[first];
            items[first] = null;
            size -= 1;
            return x;
        }
    }

    /** Removes and returns the item at the back of the deque. */
    public Item removeLast(){
        if (this.isEmpty()){
            return null;
        } else {
            if (this.isSparse()){
                this.resize(size/2);
            }
            last = minusOne(last);
            Item x = items[last];
            items[last] = null;
            size -= 1;
            return x;
        }
    }

    /** Gets the item at the given index. */
    public Item get(int index){
        if (index >= size){
            return null;
        } else {
            return items[(first + 1 + index) % items.length ];
        }
    }

    /** Create a deep copy of other. */
    public ArrayDeque(ArrayDeque other){
        items = (Item[]) new Object[other.items.length];
        first = other.first;
        last = other.last;
        size = other.size;

        System.arraycopy(other.items, 0, items, 0, other.items.length);
    }

}

import com.sun.jdi.Value;

import java.security.Key;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{

    private Node root;

    private class Node {
        private K key;
        private V value;
        private Node left, right;
        private int size;
        private Node(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    public BSTMap() {
    }

    @Override
    public void clear() {
        root = null;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        if (key == null) throw new IllegalArgumentException("calls containsKey() with a null key");
        return get(key) != null;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */

    private V get(Node node, K key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        if (node == null) {return null;}
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {return get(node.left, key);}
        else if (cmp > 0) {return get(node.right, key);}
        else return node.value;
    }


    @Override
    public V get(K key) {
        return get(root, key);
    };

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size(root);
    };

    private int size(Node node) {
        if (node == null) return 0;
        else return node.size;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key or null value");
        root = put(root, key, value);
    };

    private Node put(Node node, K key, V value) {
        if (node == null) {return new Node(key, value, 1);}
        int cmp = key.compareTo(node.key);
        if (cmp<0) {node.left = put(node.left, key, value);}
        else if (cmp>0) {node.right = put(node.right, key, value);}
        else node.value = value;
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("Unsupported operation");
    };

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("Unsupported operation");
    };

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("Unsupported operation");
    };

    @Override
    public Iterator<K> iterator() {
        return null;
    }

    public void printInOrder() {
        for (int i = 0; i < size(); i += 1) {
            System.out.println(select(i).key + " " + select(i).value);
        }
    }

    /* printInOrder() helper.
     * Return the Node with (k + 1)st smallest key.
     */
    private Node select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException();
        }
        return select(root, k);
    }

    /* printInOrder() helper.
     * Return key of rank k.
     */
    private Node select(Node x, int k) {
        if (x == null) {
            return null;
        }
        int t = size(x.left);
        if (t > k) {
            return select(x.left, k);
        } else if (t < k) {
            return select(x.right, k - t - 1);
        } else {
            return x;
        }
    }

    public static void main(String[] args) {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        int i = 0;
        b.put("hi" + i, 1+i);
        System.out.println(b.get("hi" + i));
    }
}

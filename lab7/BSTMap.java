import com.sun.jdi.Value;

import java.security.Key;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
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
    @Override
    public V get(K key) {
        return get(root, key);
    };
    private V get(Node node, K key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        if (node == null) {return null;}
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {return get(node.left, key);}
        else if (cmp > 0) {return get(node.right, key);}
        else return node.value;
    }

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
        Set<K> KeySet = new HashSet<>();
        for (int i = 0; i < size(); i += 1) {
            KeySet.add(select(i).key);
        }
        return KeySet;
    };

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        if (key == null) throw new IllegalArgumentException();
        return remove_helper(key, null);
    };


    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        if (key == null) throw new IllegalArgumentException();
        if (value == null) throw new IllegalArgumentException();
        return remove_helper(key, value);
    };

    private V remove_helper(K key, V value) {
        V tar_value = get(key);
        if (tar_value == null) {return null;} /* key not found. */
        if (value != null && !tar_value.equals(value)) {return null;}
        root = delete(root, key);
        return tar_value;
    }

    public void delete(K key) {
        if (key == null) throw new IllegalArgumentException("calls delete() with a null key");
        root = delete(root, key);
    }

    private Node delete(Node x, K key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = delete(x.left,  key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left  == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public K min() {
        if (size() == 0) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }

    public void deleteMin() {
        if (size() == 0) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /** Don't know how to write iterator. */
    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }

    private class BSTMapIterator implements Iterator<K> {
        private int pos;
        public BSTMapIterator() {
            pos = 0;
        }
        @Override
        public boolean hasNext() {
            return pos < size();
        }
        @Override
        public K next() {
            K key = select(pos).key;
            pos += 1;
            return key;
        }
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
        for (int i = 0; i < 50; i++) {
            b.put("hi" + i, 1 + i);}
        for (String s : b) {
            System.out.println(s);
        }
    }
}

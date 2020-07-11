import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class MyHashMap<K, V> implements Map61B<K, V>{

    private int M;
    private double loadFactor;
    private ULLMap<K, V>[] hashArray;
    private Set<K> keySet = new HashSet<>();

    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.M = initialSize;
        this.loadFactor = loadFactor;
        hashArray = new ULLMap[initialSize];
        for (int i = 0; i < initialSize; i++) {
            hashArray[i] = new ULLMap<>();
        }
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        for (int i = 0; i < M; i++) {
            hashArray[i].clear();
        }
        keySet.clear();
    }

    private int hash(K key, int M) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return hashArray[hash(key, M)].containsKey(key);
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (containsKey(key)) {
            return hashArray[hash(key, M)].get(key);
        } else {
            return null;
        }
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return keySet.size();
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value) {
        hashArray[hash(key, M)].put(key, value);
        keySet.add(key);
        double factor = size() / M;
        if (factor > loadFactor) {
            resize(2 * M);
        }
    }

    private void resize(int n) {
        ULLMap<K, V>[] newHashArray = new ULLMap[n];
        for (int i = 0; i < n; i++) {
            newHashArray[i] = new ULLMap<>();
        }
        for (K key : keySet) {
            newHashArray[hash(key, n)].put(key, get(key));
        }
        this.M = n;
        this.hashArray = newHashArray;
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return keySet;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    public Iterator<K> iterator() {
        return keySet.iterator();
    }

}

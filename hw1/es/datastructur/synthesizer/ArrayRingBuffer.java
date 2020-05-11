package es.datastructur.synthesizer;
import java.util.Iterator;
import java.util.Objects;

import static org.junit.Assert.assertFalse;



public class ArrayRingBuffer<T> implements BoundedQueue<T>, Iterable<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;
    private int capacity;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int c) {

        rb = (T[]) new Object[c];
        first = 0;
        last = 0;
        fillCount = 0;
        capacity = c;

    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {

        if (fillCount == rb.length) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last = (last + 1 + rb.length) % rb.length;
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T temp = rb[first];
        rb[first] = null;
        first = (first + 1 + rb.length) % rb.length;
        fillCount -= 1;
        return temp;
    }


    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];

    }

    /** create an arb iterator. */
    public class ArbIterator implements Iterator<T> {
        private int pos = 0;
        @Override
        public boolean hasNext() {
            return pos<fillCount;
        }

        @Override
        public T next() {
            T item = rb[(first + pos) % capacity];
            pos += 1;
            return item;
        }
    }

    /** call the arb iterator. */
    public Iterator<T> iterator() {
        return new ArbIterator();
    }


    /** override the equals method from Object. */
    @Override
    public boolean equals (Object other) {
        ArrayRingBuffer<T> o = (ArrayRingBuffer<T>) other;
        if (o.fillCount() != this.fillCount) {
            return false;
        }
        Iterator<T> a = this.iterator();
        Iterator<T> b = o.iterator();
        for (int i = 0; i<fillCount; i++){
            if (!a.next().equals(b.next())) {
                return false;
            }
        }
        return true;
    }

}


package es.datastructur.synthesizer;

public class Harp {
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public Harp(double frequency) {

        int capacity = (int) Math.round(SR / frequency * 2);
        buffer = new ArrayRingBuffer<>(capacity);
        for (int i = 0; i<capacity; i++) {
            buffer.enqueue(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {

        for (int i = 0; i<buffer.capacity(); i++) {
            double r = Math.random() - 0.5;
            buffer.dequeue();
            buffer.enqueue(r);
        }


    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        
        double f = buffer.dequeue();
        double s = buffer.peek();
        double a = (f + s) * 0.5 * DECAY;
        buffer.enqueue(-a);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {

        return buffer.peek();
    }
}

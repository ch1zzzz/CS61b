package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Double> arb = new ArrayRingBuffer(4);
        assertTrue(arb.isEmpty());

        arb.enqueue(9.3);
        arb.enqueue(15.1);
        arb.enqueue(31.2);
        assertFalse(arb.isEmpty());
        assertFalse(arb.isFull());
        arb.enqueue(-3.1);
        assertTrue(arb.isFull());
        double temp1 = arb.peek();
        assertEquals(9.3, temp1,0.01);
        double temp2 = arb.dequeue();
        assertEquals(9.3, temp2,0.01);
        double temp3 = arb.peek();
        assertEquals(15.1, temp3,0.01);


    }
}

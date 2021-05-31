import edu.princeton.cs.algs4.Queue;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<String> q = new Queue<>();
        q.enqueue("a");
        q.enqueue("d");
        q.enqueue("c");
        q.enqueue("b");
        q = QuickSort.quickSort(q);
        assertTrue(isSorted(q));
    }

    @Test
    public void testMergeSort() {
        Queue<String> q = new Queue<>();
        q.enqueue("a");
        q.enqueue("d");
        q.enqueue("c");
        q.enqueue("b");
        q = MergeSort.mergeSort(q);
        assertTrue(isSorted(q));
    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}

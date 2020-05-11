import org.junit.Test;
import static org.junit.Assert.*;

public class UnionTest {

    @Test
    public void UnionTest() {
        UnionFind u = new UnionFind(16);
        u.union(1,2);
        u.union(2,3);
        u.union(3,4);
        u.union(4,0);
        u.union(5,6);
        u.union(6,7);
        u.union(7,8);
        u.union(8,9);
        u.union(10,11);
        u.union(11,12);
        u.union(12,13);
        u.union(10,14);
        u.union(11,15);
        assertTrue(u.connected(10,15));
        assertTrue(u.connected(5,9));
        assertTrue(u.connected(1,0));
        assertEquals(5,u.sizeOf(0));
        assertEquals(5,u.sizeOf(5));
        assertEquals(6,u.sizeOf(10));

    }
}

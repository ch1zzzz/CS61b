import org.junit.Test;
import static org.junit.Assert.*;

public class BubbleGridTest {

    @Test
    public void testBasic() {

        int[][] grid = {{1, 1, 0},
                        {1, 0, 0},
                        {1, 1, 0},
                        {1, 1, 1}};
        int[][] darts = {{2, 0}, {0, 0}, {0, 1}};
        int[] expected = {4, 1, 0};

        validate(grid, darts, expected);
    }

    private void validate(int[][] grid, int[][] darts, int[] expected) {
        BubbleGrid sol = new BubbleGrid(grid);
        assertArrayEquals(expected, sol.popBubbles(darts));
    }

    @Test
    public void testUnion() {
        int[][] grid = {{1, 0, 0, 0},
                        {1, 1, 1, 0}};
        BubbleGrid bg = new BubbleGrid(grid);
        UnionFind uf = new UnionFind(8);
        bg.unionRound(1,1,grid,uf);
        assertTrue(uf.connected(6,5));
        assertTrue(uf.connected(6,7));
        assertFalse(uf.connected(1,5));
    }
}

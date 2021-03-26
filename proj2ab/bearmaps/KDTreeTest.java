package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {

    @Test
    public void testNearest() {
        List<Point> pointlist = new ArrayList<>();
        List<Point> testlist = new ArrayList<>();
        Random rand =new Random(25);

        for(int i = 0; i < 100000; i++) {
            pointlist.add(new Point(rand.nextDouble()* 10000, rand.nextDouble()* 10000));
        }
        for(int i = 0; i < 10000; i++) {
            testlist.add(new Point(rand.nextDouble()* 10000, rand.nextDouble()* 10000));
        }

        long start1 = System.currentTimeMillis();
        KDTree kdt = new KDTree(pointlist);
        long end1 = System.currentTimeMillis();
        System.out.println("After 100000 insert, KDTree time spends: " + (end1 - start1) + "ms.");

        long start2 = System.currentTimeMillis();
        NaivePointSet nps = new NaivePointSet(pointlist);
        long end2 = System.currentTimeMillis();
        System.out.println("After 100000 insert, NaiveSet time spends: " + (end2 - start2) + "ms.");

        long start3 = System.currentTimeMillis();
        for (Point p : testlist) {
            Point nearest = kdt.nearest(p.getX(), p.getY());
        }
        long end3 = System.currentTimeMillis();
        System.out.println("After 10000 query, KDTree time spends: " + (end3 - start3) + "ms.");

        long start4 = System.currentTimeMillis();
        for (Point p : testlist) {
            Point nearest = nps.nearest(p.getX(), p.getY());
        }
        long end4 = System.currentTimeMillis();
        System.out.println("After 10000 query, NaiveSet time spends: " + (end4 - start4) + "ms.");

        for (Point p : testlist) {
            Point nearest1 = nps.nearest(p.getX(), p.getY());
            Point nearest2 = kdt.nearest(p.getX(), p.getY());
            assertEquals(nearest1, nearest2);
        }
    }

    @Test
    public void testSimpleN() {
        Point p1 = new Point(2, 3); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 5);
        Point p4 = new Point(3, 3);
        Point p5 = new Point(1, 5);
        Point p6 = new Point(4, 4);
        KDTree kdt = new KDTree(List.of(p1, p2, p3, p4, p5, p6));
        NaivePointSet nps = new NaivePointSet(List.of(p1, p2, p3, p4, p5, p6));
        Point expected = new Point(1,5);
        Point actual = kdt.nearest(0,7);
        assertEquals(expected, actual);
        Point actual1 = nps.nearest(0,7);
        assertEquals(expected, actual1);
    }
}

package bearmaps;

import java.util.List;

public class NaivePointSet implements PointSet{
    List<Point> p;

    public NaivePointSet(List<Point> points) {
        p = points;
    }

    @Override
    public Point nearest(double x, double y) {
        Point aim = new Point(x, y);
        double min = Point.distance(p.get(0), aim);
        Point nearest = p.get(0);
        for (Point point : p) {
            if (Point.distance(point, aim) < min) {
                min = Point.distance(point, aim);
                nearest = point;
            }
        }
        return nearest;
    }
}

package bearmaps;

import java.util.List;
import java.util.SplittableRandom;

import static bearmaps.Point.distance;

public class KDTree implements PointSet{

    Node root;

    public KDTree(List<Point> points) {
        for (Point point : points) {
            root = add(root, point, false);
        }
    }

    @Override
    public Point nearest(double x, double y) {
        return nearest(root, new Point(x, y), root).p;
    }

    private class Node {
        Point p;
        Node left;
        Node right;
        boolean axis = true;  // true means x axis & false means y axis.

        private Node(Point point) {
            p = point;
        }
    }

    /**
     * add a point to the KdTree based on the parent node's axis.
     * help method for the KdTree Constructor.
     */
    private Node add(Node n, Point p, boolean axis) {
        if (n == null) {
            Node node = new Node(p);
            node.axis = !axis;
            return node;
        }

        if (n.axis) {
            // compare x
            if (p.getX() < n.p.getX()) {
                n.left = add(n.left, p, n.axis);
            } else {

                n.right = add(n.right, p, n.axis);
            }
        } else {
            //compare y
            if (p.getY() < n.p.getY()) {
                n.left = add(n.left, p, n.axis);
            } else {
                n.right = add(n.right, p, n.axis);
            }
        }
        return n;
    }

    public Node nearest(Node n, Point goal, Node best) {

        if (n == null) {return best;}

        if (distance(n.p, goal) < distance(best.p, goal)) {
            best = n;
        }

        Node goodSide, badSide;
        if (n.axis) {
            if(goal.getX() < n.p.getX()) {
                goodSide = n.left;
                badSide = n.right;
            }
            else {
                goodSide = n.right;
                badSide = n.left;
            }
        } else {
            if(goal.getY() < n.p.getY()) {
                goodSide = n.left;
                badSide = n.right;
            }
            else {
                goodSide = n.right;
                badSide = n.left;
            }
        }

        best = nearest(goodSide, goal, best);

        double badMinDis = n.axis ? Math.pow(goal.getX() - n.p.getX(), 2) : Math.pow(goal.getY() - n.p.getY(), 2);
        if (distance(best.p, goal) > badMinDis) {
            best = nearest(badSide, goal, best);
        }

        return best;
    }
}

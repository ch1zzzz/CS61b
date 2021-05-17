package bearmaps.hw4;
import bearmaps.proj2ab.DoubleMapPQ;
import bearmaps.proj2ab.ExtrinsicMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    /***
     * AStarSolver: Constructor which finds the solution, computing everything necessary for all
     * other methods to return their results in constant time. Note that timeout passed in is in seconds.
     * result: Returns one of SolverOutcome.SOLVED, SolverOutcome.TIMEOUT, or SolverOutcome.UNSOLVABLE.
     * Should be SOLVED if the AStarSolver was able to complete all work in the time given.
     * UNSOLVABLE if the priority queue became empty. TIMEOUT if the solver ran out of time.
     * You should check to see if you have run out of time every time you dequeue.
     * solution: A list of vertices corresponding to a solution. Should be empty if result was TIMEOUT or UNSOLVABLE.
     * solutionWeight: The total weight of the given solution, taking into account edge weights. Should be 0 if result was TIMEOUT or UNSOLVABLE.
     * numStatesExplored: The total number of priority queue dequeue operations.
     * explorationTime: The total time spent in seconds by the constructor.
     */

    private SolverOutcome outcome;
    private double solutionWeight = 0;
    private LinkedList<Vertex> solution;
    private double timeSpent;
    private int numStatesExplored;

    private ExtrinsicMinPQ<Vertex> fringe;
    private HashMap<Vertex, Double> distTo;
    private HashMap<Vertex, Vertex> edgeTo;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        fringe = new DoubleMapPQ<>();
        distTo = new HashMap<>();
        edgeTo = new HashMap<>();
        solution = new LinkedList<>();

        distTo.put(start, 0.0);
        fringe.add(start, 0.0);
        while (fringe.size() != 0) {
            Vertex p = fringe.getSmallest();

            //1. find the path in time
            if(p.equals(end)) {
                outcome = SolverOutcome.SOLVED;
                timeSpent = sw.elapsedTime();
                solutionWeight = distTo.get(p);
                solution.add(p);
                while (!p.equals(start)) {
                    p = edgeTo.get(p);
                    solution.addFirst(p);
                }
                return;
            }

            //2. time run out
            if (sw.elapsedTime() > timeout) {
                outcome = SolverOutcome.TIMEOUT;
                timeSpent = sw.elapsedTime();
                return;
            }

            // relax all the edge from p
            fringe.removeSmallest();
            numStatesExplored += 1;
            List<WeightedEdge<Vertex>> pNeighbors = input.neighbors(p);
            for (WeightedEdge<Vertex> neighbor : pNeighbors) {
                relax(neighbor, input, end);
            }
        }

        //3. fringe is empty and can't find the path
        outcome = SolverOutcome.UNSOLVABLE;
        timeSpent = sw.elapsedTime();
    }

    private void relax(WeightedEdge<Vertex> neighbor, AStarGraph<Vertex> input, Vertex end) {
        Vertex p = neighbor.from();
        Vertex q = neighbor.to();
        double w = neighbor.weight();
        double dis = distTo.get(p);
        if (!distTo.containsKey(q)) {
            distTo.put(q, Double.POSITIVE_INFINITY);
        }
        if (dis + w < distTo.get(q)) {
            distTo.replace(q, dis + w);
            edgeTo.put(q, p);
            if (fringe.contains(q)) {
                fringe.changePriority(q, distTo.get(q) + input.estimatedDistanceToGoal(q, end));
            } else {
                fringe.add(q, distTo.get(q) + input.estimatedDistanceToGoal(q, end));
            }
        }
    }

    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    @Override
    public List<Vertex> solution() {
        return solution;
    }

    @Override
    public double solutionWeight() {
        return solutionWeight;
    }

    @Override
    public int numStatesExplored() {
        return numStatesExplored;
    }

    @Override
    public double explorationTime() {
        return timeSpent;
    }
}

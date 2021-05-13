package bearmaps.hw4;
import bearmaps.proj2ab.DoubleMapPQ;
import bearmaps.proj2ab.ExtrinsicMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

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
    private double solutionWeight;
    private List<Vertex> solution;
    private double timeSpent;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        /// new hashdict, distTo, edgeTo, int n, 每遍历一个新的v, +dict, n++,dist/edgeTO 修改
        ExtrinsicMinPQ<Vertex> pq = new DoubleMapPQ<>();

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
        return 1;
    }

    @Override
    public double explorationTime() {
        return 1;
    }
}

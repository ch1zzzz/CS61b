package creatures;
import edu.princeton.cs.algs4.StdRandom;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Clorus extends Creature {
    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * creates clorus with energy equal to E.
     */
    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    /**
     * creates a clorus with energy equal to 1.
     */
    public Clorus() {
        this(1);
    }


    public Color color() {
        return color(r, g, b);
    }


    public void attack(Creature c) {
        energy += c.energy();
    }


    public void move() {
        energy -= 0.03;
    }


    public void stay() {
        energy -= 0.01;
    }


    public Clorus replicate() {
        Clorus offspring = new Clorus(0.5 * this.energy());
        energy = 0.5 * energy;
        return offspring;
    }

    /**
     * Plips take exactly the following actions based on NEIGHBORS:
     * 1. If no empty adjacent spaces, STAY.
     * 2. Otherwise, if energy >= 1, REPLICATE towards an empty direction
     * chosen at random.
     * 3. Otherwise, if any Cloruses, MOVE with 50% probability,
     * towards an empty direction chosen at random.
     * 4. Otherwise, if nothing else, STAY
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {

        List<Direction> emptyNeighbors = new ArrayList<>();
        List<Direction> plipNeighbors = new ArrayList<>();
        boolean anyPlips = false;
        for (Direction i : neighbors.keySet()) {
            if (neighbors.get(i).name().equals("empty")) {
                emptyNeighbors.add(i);
            } else if (neighbors.get(i).name().equals("plip")) {
                plipNeighbors.add(i);
                anyPlips = true;
            }
        }

        int empty_size = emptyNeighbors.size();
        int plip_size = plipNeighbors.size();

        if (emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        } else if (anyPlips) {
            int r = StdRandom.uniform(plip_size);
            Direction d = plipNeighbors.get(r);
            return new Action(Action.ActionType.ATTACK, d);
        } else if (energy >= 1) {
            int r = StdRandom.uniform(empty_size);
            Direction d = emptyNeighbors.get(r);
            return new Action(Action.ActionType.REPLICATE, d);
        } else {
            int r = StdRandom.uniform(empty_size);
            Direction d = emptyNeighbors.get(r);
            return new Action(Action.ActionType.MOVE, d);
        }
    }

}



package creatures;

import huglife.*;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestClorus {

    @Test
    public void testBasics() {
        Clorus c = new Clorus(2);
        assertEquals(2, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(1.97, c.energy(), 0.01);
        c.move();
        assertEquals(1.94, c.energy(), 0.01);
        c.stay();
        assertEquals(1.93, c.energy(), 0.01);
        c.stay();
        assertEquals(1.92, c.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus c = new Clorus(2);
        Clorus cr1 = c.replicate();
        assertEquals(c.energy(), cr1.energy(), 0.01);
        assertEquals(c.energy(), 1, 0.01);
    }

    @Test
    public void testChoose() {

        // No empty adjacent spaces; stay.
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);


        // Energy >= 1; replicate towards an empty space.
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);

        assertEquals(expected, actual);


        // Energy >= 1; replicate towards an empty space.
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> hasPlip = new HashMap<Direction, Occupant>();
        hasPlip.put(Direction.TOP, new Empty());
        hasPlip.put(Direction.BOTTOM, new Plip());
        hasPlip.put(Direction.LEFT, new Empty());
        hasPlip.put(Direction.RIGHT, new Empty());

        actual = c.chooseAction(hasPlip);
        expected = new Action(Action.ActionType.ATTACK, Direction.BOTTOM);

        assertEquals(expected, actual);


        // Energy < 1; stay.
        c = new Clorus(.99);

        HashMap<Direction, Occupant> moveset = new HashMap<Direction, Occupant>();
        moveset.put(Direction.TOP, new Empty());
        moveset.put(Direction.BOTTOM, new Impassible());
        moveset.put(Direction.LEFT, new Impassible());
        moveset.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(moveset);
        expected = new Action(Action.ActionType.MOVE, Direction.TOP);

        assertEquals(expected, actual);





        // We don't have Cloruses yet, so we can't test behavior for when they are nearby right now.
    }
}



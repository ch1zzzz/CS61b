import edu.princeton.cs.algs4.Queue;

import java.util.ArrayList;
import java.util.List;


/**
 * BnBSolver for the Bears and Beds problem. Each Bear can only be compared to Bed objects and each Bed
 * can only be compared to Bear objects. There is a one-to-one mapping between Bears and Beds, i.e.
 * each Bear has a unique size and has exactly one corresponding Bed with the same size.
 * Given a list of Bears and a list of Beds, create lists of the same Bears and Beds where the ith Bear is the same
 * size as the ith Bed.
 */
public class BnBSolver {
    private List<Bear> orderBears;
    private List<Bed> orderBeds;

    public BnBSolver(List<Bear> bears, List<Bed> beds) {
        Pair<List<Bear>, List<Bed>> sortPair = quickSort(bears, beds);
        orderBears = sortPair.first();
        orderBeds = sortPair.second();
    }

    /**
     * Returns List of Bears such that the ith Bear is the same size as the ith Bed of solvedBeds().
     */
    public List<Bear> solvedBears() {
        return orderBears;
    }

    /**
     * Returns List of Beds such that the ith Bear is the same size as the ith Bear of solvedBears().
     */
    public List<Bed> solvedBeds() {
        return orderBeds;
    }

    public Pair<List<Bear>, List<Bed>> quickSort(List<Bear> bears, List<Bed> beds) {
        if (bears.size() <= 1) {
            return new Pair<>(bears, beds);
        }
        Bear bearPivot = getRandomItem(bears);
        Bed bedPivot;

        List<Bear> lessBear = new ArrayList<>();
        List<Bear> equalBear = new ArrayList<>();
        List<Bear> greaterBear = new ArrayList<>();
        List<Bed> lessBed = new ArrayList<>();
        List<Bed> equalBed = new ArrayList<>();
        List<Bed> greaterBed = new ArrayList<>();


        partitionBed(beds, bearPivot, lessBed, equalBed, greaterBed);
        bedPivot = equalBed.get(0);
        partitionBear(bears, bedPivot, lessBear, equalBear, greaterBear);

        Pair<List<Bear>, List<Bed>>  lessSorted = quickSort(lessBear, lessBed);
        Pair<List<Bear>, List<Bed>> equalSorted = new Pair<>(equalBear, equalBed);
        Pair<List<Bear>, List<Bed>> greaterSorted = quickSort(greaterBear, greaterBed);

        Pair<List<Bear>, List<Bed>> sorted;
        sorted = catenate(lessSorted,equalSorted);
        sorted = catenate(sorted,greaterSorted);
        return sorted;
    }

    private static <Item extends Comparable> Item getRandomItem(List<Item> items) {
        int pivotIndex = (int) (Math.random() * items.size());
        Item pivot = items.get(pivotIndex);
        return pivot;
    }

    private void partitionBed(
            List<Bed> unsortedBed, Bear bearPivot,
            List<Bed> lessBed, List<Bed> equalBed, List<Bed> greaterBed) {

        for (Bed bed : unsortedBed) {
            if (bed.compareTo(bearPivot) < 0) {
                lessBed.add(bed);
            } else if (bed.compareTo(bearPivot) > 0) {
                greaterBed.add(bed);
            } else {
                equalBed.add(bed);
            }
        }
    }

    private void partitionBear(
            List<Bear> unsortedBear, Bed bedPivot,
            List<Bear> lessBear, List<Bear> equalBear, List<Bear> greaterBear) {

        for (Bear bear : unsortedBear) {
            if (bear.compareTo(bedPivot) < 0) {
                lessBear.add(bear);
            } else if (bear.compareTo(bedPivot) > 0) {
                greaterBear.add(bear);
            } else {
                equalBear.add(bear);
            }
        }
    }

    private static Pair<List<Bear>, List<Bed>> catenate(Pair<List<Bear>, List<Bed>> p1, Pair<List<Bear>, List<Bed>> p2) {
        p1.first().addAll(p2.first());
        p1.second().addAll(p2.second());
        Pair<List<Bear>, List<Bed>> catenated = new Pair<>(p1.first(), p1.second());
        return catenated;
    }
}

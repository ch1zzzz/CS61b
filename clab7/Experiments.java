import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hug.
 */
public class Experiments {
    /** draw a picture of average depth of BST and optimal BST. */
    public static void experiment1() {
        BST<Integer> bst = new BST<>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i<5000; i++) {
            list.add(i);
        }
        Collections.shuffle(list);

        List<Double> yValues = new ArrayList<>();
        List<Double> y2Values = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        for (int x = 1; x <= 5000; x += 1) {
            bst.add(list.get(x - 1));
            xValues.add(x);
            yValues.add(bst.averageDepth());
            y2Values.add(ExperimentHelper.optimalAverageDepth(x));
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("average depth", xValues, yValues);
        chart.addSeries("average depth of an optimal BST", xValues, y2Values);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment2() {
        BST<Integer> bst = new BST<>();
        List<Double> yValues = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        for (int i = 0; i<5000; i++) {
            ExperimentHelper.randomInsert(bst);
        }
        yValues.add(bst.averageDepth());
        xValues.add(0);
        for (int i = 1; i<=100000; i++) {
            bst.deleteTakingSuccessor(bst.getRandomKey());
            ExperimentHelper.randomInsert(bst);
            yValues.add(bst.averageDepth());
            xValues.add(i);
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("average depth", xValues, yValues);

        new SwingWrapper(chart).displayChart();

    }

    public static void experiment3() {
        BST<Integer> bst = new BST<>();
        List<Double> yValues = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        for (int i = 0; i<5000; i++) {
            ExperimentHelper.randomInsert(bst);
        }
        yValues.add(bst.averageDepth());
        xValues.add(0);
        for (int i = 1; i<=150000; i++) {
            bst.deleteTakingRandom(bst.getRandomKey());
            ExperimentHelper.randomInsert(bst);
            yValues.add(bst.averageDepth());
            xValues.add(i);
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("average depth", xValues, yValues);

        new SwingWrapper(chart).displayChart();
    }

    public static void main(String[] args) {
        experiment3();
    }
}

package com.github.rubenqba.pso.ui;

import com.github.rubenqba.pso.Swarm;
import com.github.rubenqba.pso.movement.BDF2Movement;
import com.github.rubenqba.pso.movement.StandardMovement;
import com.github.rubenqba.pso.problem.SphereProblem;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A demo showing the display of JFreeChart within a JavaFX application.
 * For more information about the JFreeSVG vs Batik performance test, see
 * this link: http://www.object-refinery.com/blog/blog-20140423.html
 */
public class BarChartFXDemo1 extends Application {

    /**
     * Returns a sample dataset.
     *
     * @return The dataset.
     */
    private static XYSeriesCollection createDataset() {

        Swarm swarm = new Swarm();

        SphereProblem p1 = new SphereProblem() {
            @Override
            public double getW() {
                return 0.6;
            }

            @Override
            public double getWDamp() {
                return 0.99;
            }

            @Override
            public double getC1() {
                return 1.7;
            }

            @Override
            public double getC2() {
                return 1.7;
            }

            @Override
            public int getProblemDimension() {
                return 2;
            }

            @Override
            public double getErrorTolerance() {
                return 1E-2;
            }

            @Override
            public int getMaximumIterations() {
                return 1000;
            }

            @Override
            public String getName() {
                return "SphereModifiedProblem";
            }
        };

        XYSeriesCollection dataset = new XYSeriesCollection();

        Stream.of(50/*, 100, 200, 500*/)
                .forEach(s ->
                        Stream.of(new StandardMovement(), new BDF2Movement())
                                .forEach(m -> {
                                    List<Double> bestValue = new ArrayList<>();
                                    swarm.setMovement(m);
                                    p1.setSwarmSize(s);
                                    swarm.execute(p1, bestValue);

                                    XYSeries serie = new XYSeries(m.getName());

                                    IntStream.range(0, bestValue.size())
                                            .forEach(i -> serie.add(i, bestValue.get(i).doubleValue()));

                                    dataset.addSeries(serie);
                                })
                );

        return dataset;
    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(XYSeriesCollection dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
            "Performance: PSO-Std vs PSO-IT2", "Iteraciones",
                "BestValue", dataset);

        chart.setBackgroundPaint(Color.white);
        XYPlot plot = (XYPlot) chart.getPlot();

        LogAxis yAxis = new LogAxis("Y");
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        yAxis.setLabel("BestValue");
        plot.setRangeAxis(yAxis);

        chart.getLegend().setFrame(BlockBorder.NONE);
        return chart;
    }

    /**
     * Adds a chart viewer to the stage and displays it.
     * 
     * @param stage  the stage.
     * @throws Exception if something goes wrong.
     */
    @Override 
    public void start(Stage stage) throws Exception {
        XYSeriesCollection dataset = createDataset();
        JFreeChart chart = createChart(dataset); 
        ChartViewer viewer = new ChartViewer(chart);
        stage.setScene(new Scene(viewer));
        stage.setTitle("JFreeChart: BarChartFXDemo1.java"); 
        stage.setWidth(700);
        stage.setHeight(390);
        stage.show(); 
    }

    /**
     * Entry point.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }



}
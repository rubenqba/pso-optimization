package com.github.rubenqba.pso.problem;

import lombok.Data;

import java.util.Arrays;

/**
 * Created by ruben.bressler on 15/02/17.
 */
@Data
public abstract class CommonProblemSet {

    private int swarmSize = 30;

    private int problemDimension = 30;

//    return w_UPPERBOUND - (((double) iteration) / getMaximumIterations()) * (w_UPPERBOUND - w_LOWERBOUND);
    private double w = .6;
    private double wDamp = 1;
    private double c1 = 1.7;
    private double c2 = 1.7;

    private double minimumByDimension = -100;
    private double maximumByDimension = 100;

    private double errorTolerance = 1E-2;
    private int maximumIterations = 1000;
    private StopCondition stopCondition = StopCondition.BOTH;

    public CommonProblemSet() {
        setW(.6);
        setWDamp(.99);
        setC1(1.7);
        setC2(1.7);
        setProblemDimension(2);
        setMaximumIterations(1000);
        setErrorTolerance(1E-2);
    }

    public String getName() {
        return getClass().getSimpleName();
    }

    abstract public double evaluate(double[] vars);

    public double[] getMinimumLocation() {
        double[] array = new double[getProblemDimension()];
        Arrays.fill(array, getMinimumByDimension());
        return array;
    }

    public double[] getMaximumLocation() {
        double[] array = new double[getProblemDimension()];
        Arrays.fill(array, getMaximumByDimension());
        return array;
    }

    public double getOptimum() {
        return 0;
    }
}

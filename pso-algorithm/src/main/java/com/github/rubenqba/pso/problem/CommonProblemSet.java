package com.github.rubenqba.pso.problem;

import lombok.Data;

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

    private double[] minimumLocation;
    private double[] maximumLocation;

    private double errorTolerance = 1E-2;
    private int maximumIterations = 1000;
    private StopCondition stopCondition = StopCondition.BOTH;

    public String getName() {
        return getClass().getSimpleName();
    }

    abstract public double evaluate(double[] vars);

    public double getOptimum() {
        return 0;
    }
}

package com.github.rubenqba.pso.problem;

/**
 * Created by ruben.bressler on 8/02/17.
 */
public class LinearProblem extends CommonProblemSet {
    @Override
    public int getProblemDimension() {
        return 1;
    }

    @Override
    public double[] getMinimumLocation() {
        return new double[]{0};
    }

    @Override
    public double[] getMaximumLocation() {
        return new double[]{10};
    }

    @Override
    public double evaluate(double[] v) {
        return v[0] + 2;
    }

    @Override
    public double getOptimum() {
        return 2;
    }
}

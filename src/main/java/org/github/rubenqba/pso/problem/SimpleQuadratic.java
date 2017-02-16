package org.github.rubenqba.pso.problem;

public class SimpleQuadratic extends CommonProblemSet {
    @Override
    public int getProblemDimension() {
        return 1;
    }

    @Override
    public double[] getLocationMinimum() {
        return new double[]{0};
    }

    @Override
    public double[] getLocationMaximum() {
        return new double[]{10};
    }

    @Override
    public double evaluate(double[] v) {
        return v[0] * v[0];
    }

}
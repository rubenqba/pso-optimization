package org.github.rubenqba.pso.problem;

/**
 * Created by ruben.bressler on 8/02/17.
 */
public class LinearProblem implements PSOProblemSet {
    @Override
    public int getProblemDimension() {
        return 1;
    }

    @Override
    public double getW(int iteration) {
        return .9;
    }

    @Override
    public double getC1() {
        return .2;
    }

    @Override
    public double getC2() {
        return 3.5;
    }

    @Override
    public double[] getLocationMinimum() {
        return new double[]{-10};
    }

    @Override
    public double[] getLocationMaximum() {
        return new double[]{10};
    }

    @Override
    public double evaluate(double[] location) {
        return location[0] * location[0] + 2;
    }

    @Override
    public double getOptimum() {
        return 2;
    }
}

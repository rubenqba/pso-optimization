package com.github.rubenqba.pso.problem;

import java.util.Arrays;

/**
 * Created by ruben.bressler on 8/02/17.
 */
public class SphereProblem extends CommonProblemSet {
    @Override
    public int getProblemDimension() {
        return 30;
    }

    @Override
    public double[] getMinimumLocation() {
        double[] array = new double[getProblemDimension()];
        Arrays.fill(array, -100d);
        return array;
    }

    @Override
    public double[] getMaximumLocation() {
        double[] array = new double[getProblemDimension()];
        Arrays.fill(array, 100d);
        return array;
    }

    @Override
    public double getErrorTolerance() {
        return 1E-2;
    }

    @Override
    public double evaluate(double[] location) {
        return Arrays.stream(location)
                .map(l -> l * l)
                .sum();
    }
}

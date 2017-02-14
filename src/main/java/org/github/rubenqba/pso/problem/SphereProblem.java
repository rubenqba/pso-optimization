package org.github.rubenqba.pso.problem;

import java.util.Arrays;

/**
 * Created by ruben.bressler on 8/02/17.
 */
public class SphereProblem implements PSOProblemSet {
    @Override
    public int getProblemDimension() {
        return 30;
    }

    @Override
    public double[] getLocationMinimum() {
        double[] array = new double[getProblemDimension()];
        Arrays.fill(array, -100d);
        return array;
    }

    @Override
    public double[] getLocationMaximum() {
        double[] array = new double[getProblemDimension()];
        Arrays.fill(array, 100d);
        return array;
    }

    @Override
    public double getErrorTolerance() {
        return 1E-2;
    }

    @Override
    public int getSwarmSize() {
        return 30;
    }

    @Override
    public double evaluate(double[] location) {
        return Math.sqrt(Arrays.stream(location)
                                 .map(l -> l * l)
                                 .sum());
    }

    @Override
    public double getOptimum() {
        return 0;
    }
}

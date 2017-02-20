package org.github.rubenqba.pso.problem;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Created by ruben.bressler on 15/02/17.
 */
public class RosenbrockProblem extends CommonProblemSet {
    @Override
    public double getErrorTolerance() {
        return 1E2;
    }

    @Override
    public double[] getMinimumLocation() {
        double[] array = new double[getProblemDimension()];
        Arrays.fill(array, -30d);
        return array;
    }

    @Override
    public double[] getMaximumLocation() {
        double[] array = new double[getProblemDimension()];
        Arrays.fill(array, 30d);
        return array;
    }

    @Override
    public double evaluate(double[] x) {
        return IntStream.range(0, x.length - 1)
                .mapToDouble(i -> (100 * (x[i + 1] - x[i] * x[i]) * (x[i + 1] - x[i] * x[i])) + (x[i] - 1) * (x[i] - 1))
                .sum();
    }
}

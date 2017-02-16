package org.github.rubenqba.pso.problem;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Created by ruben.bressler on 15/02/17.
 */
public class RastriginProblem extends CommonProblemSet {

    @Override
    public double getErrorTolerance() {
        return 1E2;
    }

    @Override
    public double[] getLocationMinimum() {
        double[] array = new double[getProblemDimension()];
        Arrays.fill(array, -5.12);
        return array;
    }

    @Override
    public double[] getLocationMaximum() {
        double[] array = new double[getProblemDimension()];
        Arrays.fill(array, 5.12);
        return array;
    }

    @Override
    public double evaluate(double[] x) {
        return 10 * x.length + IntStream.range(0, x.length)
                .mapToDouble(i -> (x[i] * x[i] - 10 * Math.cos(2 * Math.PI * x[i])))
                .sum();
    }
}

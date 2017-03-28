package com.github.rubenqba.pso.problem;

import java.util.stream.IntStream;

/**
 * Created by ruben.bressler on 15/02/17.
 */
public class RosenbrockProblem extends CommonProblemSet {

    public RosenbrockProblem() {
        setErrorTolerance(1E2);
    }

    @Override
    public double evaluate(double[] x) {
        return IntStream.range(0, x.length - 1)
                .mapToDouble(i -> (100 * (x[i + 1] - x[i] * x[i]) * (x[i + 1] - x[i] * x[i])) + (x[i] - 1) * (x[i] - 1))
                .sum();
    }
}

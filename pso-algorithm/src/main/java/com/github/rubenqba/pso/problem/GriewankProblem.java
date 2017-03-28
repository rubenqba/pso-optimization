package com.github.rubenqba.pso.problem;

import java.util.stream.IntStream;

/**
 * Created by ruben.bressler on 15/02/17.
 */
public class GriewankProblem extends CommonProblemSet {

    public GriewankProblem() {
        super();
    }

    @Override
    public double evaluate(double[] x) {
        return (IntStream.range(0, x.length).mapToDouble(i -> x[i] * x[i]).sum() / 4000)
                - IntStream.range(0, x.length).mapToDouble(i -> Math.cos(x[i] / Math.sqrt(i + 1))).reduce(1, (a, b) -> a
                * b)
                + 1;
    }
}

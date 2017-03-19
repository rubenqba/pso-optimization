package com.github.rubenqba.pso.problem;

import java.util.Arrays;

/**
 * Created by ruben.bressler on 8/02/17.
 */
public class SphereProblem extends CommonProblemSet {

    public SphereProblem() {
        setW(.6);
        setWDamp(.99);
        setC1(1.7);
        setC2(1.7);
        setProblemDimension(2);
        setMaximumIterations(100);
        setErrorTolerance(1E-2);
    }

    @Override
    public double evaluate(double[] location) {
        return Arrays.stream(location)
                .map(l -> l * l)
                .sum();
    }
}

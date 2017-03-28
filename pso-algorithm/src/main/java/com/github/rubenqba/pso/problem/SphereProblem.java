package com.github.rubenqba.pso.problem;

import java.util.Arrays;

/**
 * Created by ruben.bressler on 8/02/17.
 */
public class SphereProblem extends CommonProblemSet {

    public SphereProblem() {
        super();
    }

    @Override
    public double evaluate(double[] location) {
        return Arrays.stream(location)
                .map(l -> l * l)
                .sum();
    }
}

package com.github.rubenqba.pso.problem;

/**
 * Created by ruben.bressler on 15/02/17.
 */
public class Schaffer6Problem extends CommonProblemSet {

    public Schaffer6Problem() {
        setErrorTolerance(1E-5);
    }

    @Override
    public int getProblemDimension() {
        return 2;
    }

    @Override
    public double evaluate(double[] x) {
        return 0.5
                + ((square(Math.sin(Math.sqrt(x[0] * x[0] + x[1] * x[1]))) - 0.5)
                / square(1 + 1E-3 * (x[0] * x[0] + x[1] * x[1])));
    }

    private double square(double x) {
        return x * x;
    }
}

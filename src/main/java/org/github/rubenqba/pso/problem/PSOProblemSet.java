package org.github.rubenqba.pso.problem;

public interface PSOProblemSet {

    default String getName() {
        return this.getClass().getSimpleName();
    }

    int getProblemDimension();

    default int getSwarmSize() {
        return 30;
    }

    default double getW(int iteration) {
//        double w_UPPERBOUND = 1.0;
//        double w_LOWERBOUND = 0.0;
//
//        return w_UPPERBOUND - (((double) iteration) / getMaximumIterations()) * (w_UPPERBOUND - w_LOWERBOUND);
        return .1;
    }

    default double getC1() {
        return 2;
    }

    default double getC2() {
        return 2;
    }

    default double getMinimumVelocity() {
        return -1;
    }

    default double getMaximumVelocity() {
        return 1;
    }

    default double getErrorTolerance() {
        return 1E-20;
    }

    default int getMaximumIterations() {
        return 1000;
    }

    double[] getLocationMinimum();

    double[] getLocationMaximum();

    double evaluate(double[] location);

    double getOptimum();
}
package org.github.rubenqba.pso.problem;

public interface ProblemSet {

    default String getName() {
        return this.getClass().getSimpleName();
    }

    default int getProblemDimension() {
        return 30;
    }

    int getSwarmSize();

    void setSwarmSize(int swarmSize);

    default double getW(int iteration) {
//        double w_UPPERBOUND = 1.0;
//        double w_LOWERBOUND = 0.0;
//
//        return w_UPPERBOUND - (((double) iteration) / getMaximumIterations()) * (w_UPPERBOUND - w_LOWERBOUND);
        return .6;
    }

    default double getC1() {
        return 1.7;
    }

    default double getC2() {
        return 1.7;
    }

    default double getMinimumVelocity() {
        return -10;
    }

    default double getMaximumVelocity() {
        return 10;
    }

    default double getErrorTolerance() {
        return 1E-20;
    }

    default int getMaximumIterations() {
        return 10000;
    }

    double[] getLocationMinimum();

    double[] getLocationMaximum();

    double evaluate(double[] location);

    default double getOptimum() {
        return 0;
    }
}
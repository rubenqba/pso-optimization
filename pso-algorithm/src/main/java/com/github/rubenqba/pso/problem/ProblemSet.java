package com.github.rubenqba.pso.problem;

public interface ProblemSet {

    default String getName() {
        return this.getClass().getSimpleName();
    }

    default int getProblemDimension() {
        return 30;
    }

    int getSwarmSize();

    void setSwarmSize(int swarmSize);

    default double getW() {
//        double w_UPPERBOUND = 1.0;
//        double w_LOWERBOUND = 0.0;
//
//        return w_UPPERBOUND - (((double) iteration) / getMaximumIterations()) * (w_UPPERBOUND - w_LOWERBOUND);
        return .6;
    }

    default double getWDamp() {
        return 0.99;
    }

    default double getWUpper() {
        return 1.0;
    }

    default double getWLower() {
        return 0.0;
    }

    default double getC1() {
        return 1.7;
    }

    default double getC2() {
        return 1.7;
    }

    default double getErrorTolerance() {
        return 1E-20;
    }

    default int getMaximumIterations() {
        return 10000;
    }

    double[] getMinimumLocation();

    double[] getMaximumLocation();

    double evaluate(double[] location);

    enum StopCondition {
        ERROR("Error Tolerance"),
        ITERATIONS("Max. Iterations"),
        BOTH("Both");

        private String label;

        StopCondition(String label) {
            this.label = label;
        }

        public String toString() {
            return label;
        }
    }

    default StopCondition getStopCondition() {
        return StopCondition.BOTH;
    }

    default double getOptimum() {
        return 0;
    }
}
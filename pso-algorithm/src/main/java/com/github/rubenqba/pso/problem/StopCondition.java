package com.github.rubenqba.pso.problem;

public enum StopCondition {
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
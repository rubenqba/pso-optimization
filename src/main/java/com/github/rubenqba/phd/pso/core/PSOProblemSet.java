package com.github.rubenqba.phd.pso.core;

import com.github.rubenqba.phd.pso.utils.Location;

public interface PSOProblemSet {

    int getProblemDimension();
    int getSwarmSize();

    double getW(int iteration);
    double getC1();
    double getC2();

    double getMinimumVelocity();
    double getMaximunVelocity();

    double getErrorTolerance();
    int getMaximumIterations();

    Location getLocationMinimum();
    Location getLocationMaximum();

    double evaluate(Location location);

    double getOptimum();
}

package com.github.rubenqba.phd.pso;

/* author: gandhi - gandhi.mtm [at] gmail [dot] com - Depok, Indonesia */

// this is the problem to be solved
// to find an x and a y that minimize the function below:
// f(x, y) = (2.8125 - x + x * y^4)^2 + (2.25 - x + x * y^2)^2 + (1.5 - x + x*y)^2
// where 1 <= x <= 4, and -1 <= y <= 1

// you can modify the function depends on your needs
// if your problem space is greater than 2-dimensional space
// you need to introduce a new variable (other than x and y)

import com.github.rubenqba.phd.pso.core.PSOProblemSet;
import com.github.rubenqba.phd.pso.utils.Location;

public class ProblemSet1 implements PSOProblemSet {
    public static final int SWARM_SIZE = 10;

    public static final double VEL_LOW = 0;
	public static final double VEL_HIGH = 4;
	
	public static final double ERR_TOLERANCE = 1E-20; // the smaller the tolerance, the more accurate the result,
	                                                  // but the number of iteration is increased


    private static final double W_UPPERBOUND = 1.0;
    private static final double W_LOWERBOUND = 0.0;

    @Override
    public double getW(int iteration) {
//        return W_UPPERBOUND - (((double) iteration) / getMaximumIterations()) * (W_UPPERBOUND - W_LOWERBOUND);
        return 0.9;
    }

    @Override
    public double getC1() {
        return 0.2;
    }

    @Override
    public double getC2() {
        return 3.5;
    }

    @Override
    public double getErrorTolerance() {
        return ERR_TOLERANCE;
    }

    @Override
    public int getMaximumIterations() {
        return 1000;
    }

    @Override
    public double getMaximunVelocity() {
        return VEL_HIGH;
    }

    @Override
    public double getMinimumVelocity() {
        return VEL_LOW;
    }

    @Override
    public int getProblemDimension() {
        return 1; //PROBLEM_DIMENSION;
    }

    @Override
    public int getSwarmSize() {
        return SWARM_SIZE;
    }

    public Location getLocationMinimum() {
		double[] data = {-100, -100};
		return new Location(data);
	}

	public Location getLocationMaximum() {
		double[] data = {100, 100};
		return new Location(data);
	}

	public double evaluate(Location location) {
		return quadratic(location);
	}

	public static double function(Location location) {
        double result = 0;
        double x = location.get(0); // the "x" part of the location
        double y = location.get(1); // the "y" part of the location

        result = Math.pow(2.8125 - x + x * Math.pow(y, 4), 2) +
                Math.pow(2.25 - x + x * Math.pow(y, 2), 2) +
                Math.pow(1.5 - x + x * y, 2);

        return result;
    }

	public static double sphere(Location location) {
		double result = location.productoEscalar(location);

		return result;
	}

    public static double quadratic(Location location) {
        double result = location.get(0) * location.get(0);

        return result;
    }

    @Override
    public double getOptimum() {
        return 0;
    }
}

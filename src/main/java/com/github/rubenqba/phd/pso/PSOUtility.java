package com.github.rubenqba.phd.pso;

/* author: gandhi - gandhi.mtm [at] gmail [dot] com - Depok, Indonesia */

// just a simple utility class to find a minimum position on a list

import com.github.rubenqba.phd.pso.core.PSOProblemSet;
import com.github.rubenqba.phd.pso.utils.Location;
import com.github.rubenqba.phd.pso.utils.RandomGenerator;
import com.github.rubenqba.phd.pso.utils.Vector;
import com.github.rubenqba.phd.pso.utils.Velocity;

import java.util.Random;
import java.util.stream.IntStream;

public class PSOUtility {
	public static int getMinPos(double[] list) {
		int pos = 0;
		double minValue = list[0];
		
		for(int i=0; i<list.length; i++) {
			if(list[i] < minValue) {
				pos = i;
				minValue = list[i];
			}
		}
		
		return pos;
	}

    public static Location randomLocation(Vector min, Vector max) {

        Random generator = RandomGenerator.getInstance().getRandom();

        double cord[] = new double[min.getDimension()];
        IntStream.range(0, min.getDimension())
                .forEach(i -> cord[i] = min.get(i) + generator.nextDouble() * (max.get(i) - min.get(i)));

        return new Location(cord);
    }

    public static Location randomLocation(PSOProblemSet problem) {

        Random generator = RandomGenerator.getInstance().getRandom();
        Location min = problem.getLocationMinimum();
        Location max = problem.getLocationMaximum();

        double cord[] = new double[problem.getProblemDimension()];
        IntStream.range(0, problem.getProblemDimension())
                .forEach(i -> cord[i] = min.get(i) + generator.nextDouble() * (max.get(i) - min.get(i)));

        return new Location(cord);
    }

    public static Velocity randomVelocity(PSOProblemSet problem) {
        Random generator = RandomGenerator.getInstance().getRandom();

        double cord[] = new double[problem.getProblemDimension()];
        IntStream.range(0, problem.getProblemDimension())
                .forEach(i -> cord[i] = problem.getMinimumVelocity()
                        + generator.nextDouble()
                        * (problem.getMaximunVelocity() - problem.getMinimumVelocity()));

        return new Velocity(cord);
    }
}

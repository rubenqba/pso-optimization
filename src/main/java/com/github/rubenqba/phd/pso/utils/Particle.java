package com.github.rubenqba.phd.pso.utils;

import com.github.rubenqba.phd.pso.core.PSOProblemSet;
import lombok.Data;

@Data
public class Particle {

	private double fitnessValue;
	private Velocity velocity;
	private Location location;

	private double bestFitness;
	private Location bestLocation;
	
	public Particle() {
		super();
		bestFitness = Double.MAX_VALUE;
	}

	public Particle(double fitnessValue, Velocity velocity, Location location) {
		super();
		this.fitnessValue = fitnessValue;
		this.velocity = velocity;
		this.location = location;
	}

	public double getFitnessValue(PSOProblemSet problem) {
		fitnessValue = problem.evaluate(location);

		if (bestFitness > fitnessValue) {
            bestFitness = fitnessValue;
            bestLocation = location.clone();
        }

		return fitnessValue;
	}
}

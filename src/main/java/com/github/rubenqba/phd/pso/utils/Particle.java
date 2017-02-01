package com.github.rubenqba.phd.pso.utils;

import com.github.rubenqba.phd.pso.ProblemSet;
import lombok.Data;

@Data
public class Particle {

	private double fitnessValue;
	private Velocity velocity;
	private Location location;
	
	public Particle() {
		super();
	}

	public Particle(double fitnessValue, Velocity velocity, Location location) {
		super();
		this.fitnessValue = fitnessValue;
		this.velocity = velocity;
		this.location = location;
	}

	public double getFitnessValue() {
		fitnessValue = ProblemSet.evaluate(location);
		return fitnessValue;
	}
}

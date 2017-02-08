package org.github.rubenqba.pso;

import lombok.Getter;
import lombok.Setter;
import org.github.rubenqba.pso.data.Location;
import org.github.rubenqba.pso.data.Velocity;
import org.github.rubenqba.pso.problem.PSOProblemSet;

@Getter
public class Particle {
    private double fitnessValue;

    @Setter
    private Velocity velocity;
    @Setter
    private Location location;

    private double bestFitness;
    private Location bestLocation;

    public Particle() {
        super();
        bestFitness = Double.MAX_VALUE;
    }

    public double getFitnessValue(PSOProblemSet problem) {
        fitnessValue = problem.evaluate(location.getLoc());

        if (fitnessValue < bestFitness) {
            bestFitness = fitnessValue;
            bestLocation = new Location(location);
        }
        return fitnessValue;
    }

}

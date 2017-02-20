package org.github.rubenqba.pso;

import org.github.rubenqba.pso.data.Location;
import org.github.rubenqba.pso.data.Velocity;
import org.github.rubenqba.pso.util.RandomGenerator;

import java.util.stream.IntStream;

public class StandardMovement implements Movement {

    @Override
    public void moveParticle(Swarm swarm, Particle p) {
        RandomGenerator generator = RandomGenerator.getInstance();
        double[] r1 = generator.nextDoubles(swarm.getProblem().getProblemDimension());
        double[] r2 = generator.nextDoubles(swarm.getProblem().getProblemDimension());

        // step 3 - update velocity
        // step 4 - update location
        double[] newVel = new double[swarm.getProblem().getProblemDimension()];
        double[] newLoc = new double[swarm.getProblem().getProblemDimension()];

        IntStream.range(0, swarm.getProblem().getProblemDimension())
                .forEach(i -> {
                    newVel[i] = (swarm.getProblem().getW() * p.getVelocity().getVelocity()[i]) +
                            (r1[i] * swarm.getProblem().getC1()) * (p.getBestLocation().getLoc()[i] - p.getLocation().getLoc()[i]) +
                            (r2[i] * swarm.getProblem().getC2()) * (swarm.getBestLocation().getLoc()[i] - p.getLocation().getLoc()[i]);
                    newLoc[i] = p.getLocation().getLoc()[i] + newVel[i];
                });

        p.setVelocity(new Velocity(newVel));
        p.setLocation(new Location(newLoc));
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
package com.github.rubenqba.pso.movement;

import com.github.rubenqba.pso.Movement;
import com.github.rubenqba.pso.Particle;
import com.github.rubenqba.pso.Swarm;
import com.github.rubenqba.pso.data.Location;
import com.github.rubenqba.pso.data.Velocity;
import com.github.rubenqba.pso.util.RandomGenerator;

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

        final double[] w = {swarm.getProblem().getW()};
        double wdamp = swarm.getProblem().getWDamp();

        IntStream.range(0, swarm.getProblem().getProblemDimension())
                .forEach(i -> {
                    w[0] *= wdamp;
                    newVel[i] = (w[0] * p.getVelocity().getVelocity()[i]) +
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
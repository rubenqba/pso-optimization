package org.github.rubenqba.pso;

import org.github.rubenqba.pso.data.Location;
import org.github.rubenqba.pso.data.Velocity;
import org.github.rubenqba.pso.util.RandomGenerator;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Created by ruben.bresler on 20/02/17.
 */
public class RK2Movement extends StandardMovement {

    protected int h;

    public RK2Movement() {
        h = 1;
    }

    @Override
    public void moveParticle(Swarm swarm, Particle p) {
        RandomGenerator generator = RandomGenerator.getInstance();
        double[] r1 = generator.nextDoubles(swarm.getProblem().getProblemDimension());
        double[] r2 = generator.nextDoubles(swarm.getProblem().getProblemDimension());

        // step 3 - update velocity
        // step 4 - update location
        double[] vel = Arrays.copyOf(p.getLocation().getLoc(), swarm.getProblem().getProblemDimension());
        double[] newVel = new double[swarm.getProblem().getProblemDimension()];
        double[] newLoc = new double[swarm.getProblem().getProblemDimension()];

        IntStream.range(0, swarm.getProblem().getProblemDimension())
                .forEach(i -> {
                    newVel[i] = (swarm.getProblem().getW() * p.getVelocity().getVelocity()[i]) +
                            (r1[i] * swarm.getProblem().getC1()) * (p.getBestLocation().getLoc()[i] - vel[i]) +
                            (r2[i] * swarm.getProblem().getC2()) * (swarm.getBestLocation().getLoc()[i] - vel[i]);

                    newLoc[i] = p.getLocation().getLoc()[i] + (h * (vel[i] + newVel[i])) / 2;
                });

        p.setVelocity(new Velocity(newVel));
        p.setLocation(new Location(newLoc));
    }
}

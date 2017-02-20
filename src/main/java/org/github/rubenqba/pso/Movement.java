package org.github.rubenqba.pso;

/**
 * Created by ruben.bresler on 20/02/17.
 */
public interface Movement {
    void moveParticle(Swarm swarm, Particle p);

    String getName();
}

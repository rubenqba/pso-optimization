package org.github.rubenqba.pso;

import org.github.rubenqba.pso.problem.SphereProblem;

public class PSODriver {
    public static void main(String args[]) {
        new Swarm(new SphereProblem()).execute();
    }
}
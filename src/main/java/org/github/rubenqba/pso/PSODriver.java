package org.github.rubenqba.pso;

import org.github.rubenqba.pso.problem.LinearProblem;

public class PSODriver {
    public static void main(String args[]) {
        new Swarm(new LinearProblem()).execute();
    }
}
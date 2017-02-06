package com.github.rubenqba.phd.pso;

/* author: gandhi - gandhi.mtm [at] gmail [dot] com - Depok, Indonesia */

// this is a driver class to execute the PSO process

import com.github.rubenqba.phd.pso.core.PSOProblemSet;
import com.github.rubenqba.phd.pso.utils.Swarm;

public class PSODriver {
	public static void main(String args[]) {
//		new PSOProcess().execute();

        PSOProblemSet p = new ProblemSet1();

        Swarm s = new Swarm(p);

        s.initialize();


        double tolerance = p.getErrorTolerance();
        int maxIterations = p.getMaximumIterations();
        int i=0;

        while(i < maxIterations && tolerance < s.getError()) {
            s.iterate();

            System.out.println("ITERATION " + i + ": ");
            System.out.println("     Best X: " + s.getBestLocation().get(0));
//            System.out.println("     Best Y: " + s.getBestLocation().get(1));
            System.out.println("     Value: " + s.getBestFitness());

            i++;
        }

        System.out.println("\nSolution found at iteration " + (i - 1) + ", the solutions is:");
        System.out.println("     Best X: " + s.getBestLocation().get(0));
//        System.out.println("     Best Y: " + s.getBestLocation().get(1));
        System.out.println("     Value: " + s.getBestFitness());
	}
}

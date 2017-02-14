package org.github.rubenqba.pso;

import org.github.rubenqba.pso.problem.SphereProblem;

import java.util.Locale;

public class PSODriver {
    public static void main(String args[]) {
        Locale.setDefault(Locale.US);
        Swarm s = new Swarm();

//        s.execute(new BaseLineProblem());
//        System.out.println("\nSolution of " + s.getProblem().getName() + " found at iteration " + s.getIteration() +
//                                   ", " +
//                                   "the solutions" +
//                                   " is:");
//        System.out.println("     Best : " + s.getGBestLocation());
//        System.out.println("     Value: " + s.getGBest());
//        System.out.println("     Error: " + s.getError());

//        s.execute(new LinearProblem());
//        System.out.println("\nSolution of " + s.getProblem().getName() + " found at iteration " + s.getIteration()
// + ", " +
//                                   "the solutions" +
//                                   " is:");
//        System.out.println("     Best : " + s.getGBestLocation());
//        System.out.println("     Value: " + s.getGBest());
//        System.out.println("     Error: " + s.getError());

        s.execute(new SphereProblem());
        System.out.println("\nSolution of " + s.getProblem().getName() + " found at iteration " + s.getIteration() +
                                   ", " +
                                   "the solutions" +
                                   " is:");
        System.out.println("     Best : " + s.getGBestLocation());
        System.out.println("     Value: " + s.getGBest());
        System.out.println("     Error: " + s.getError());

//        s.execute(new SimpleQuadratic());
//        System.out.println("\nSolution of " + s.getProblem().getName() + " found at iteration " + s.getIteration() +
//                                   ", " +
//                                   "the solutions" +
//                                   " is:");
//        System.out.println("     Best : " + s.getGBestLocation());
//        System.out.println("     Value: " + s.getGBest());
//        System.out.println("     Error: " + s.getError());
    }
}
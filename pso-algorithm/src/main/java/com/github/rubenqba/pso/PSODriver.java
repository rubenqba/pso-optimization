package com.github.rubenqba.pso;

import com.github.rubenqba.pso.problem.*;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class PSODriver {
    public static void main(String args[]) {
        Locale.setDefault(Locale.US);
        Swarm s = new Swarm();

        List<ProblemSet> problems = Arrays.asList(new SphereProblem(), new RosenbrockProblem(),
                new RastriginProblem(), new GriewankProblem(), new Schaffer6Problem());

        problems.stream()
                .forEach(p -> s.execute(p, null));
    }

    private void summary(Swarm s) {
        System.out.println(String.format("\nSolution of %s found at iteration %d, the solutions is:",
                s.getProblem().getName(), s.getIteration()));
        System.out.println("     Best : " + s.getBestLocation());
        System.out.println("     Value: " + s.getBestFitness());
        System.out.println("     Error: " + s.getError());
    }
}
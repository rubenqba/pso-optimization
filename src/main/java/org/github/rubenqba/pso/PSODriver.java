package org.github.rubenqba.pso;

import org.github.rubenqba.pso.problem.*;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class PSODriver {
    public static void main(String args[]) {
        Locale.setDefault(Locale.US);
        Swarm s = new Swarm();
//        s.setDebug(true);

        List<PSOProblemSet> problems = Arrays.asList(new SphereProblem(), new RosenbrockProblem(),
                new RastriginProblem(), new GriewankProblem(), new Schaffer6Problem());

        problems.stream()
                .forEach(s::execute);
    }

    private void summary(Swarm s) {
        System.out.println(String.format("\nSolution of %s found at iteration %d, the solutions is:",
                s.getProblem().getName(), s.getIteration()));
        System.out.println("     Best : " + s.getGBestLocation());
        System.out.println("     Value: " + s.getGBest());
        System.out.println("     Error: " + s.getError());
    }
}
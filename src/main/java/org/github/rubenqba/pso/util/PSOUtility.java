package org.github.rubenqba.pso.util;

import org.github.rubenqba.pso.data.Location;
import org.github.rubenqba.pso.data.Velocity;
import org.github.rubenqba.pso.problem.ProblemSet;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.IntStream;

public class PSOUtility {
    public static int getMinPos(double[] list) {
        int pos = IntStream.range(0, list.length)
                          .reduce((i, j) -> list[i] > list[j] ? j : i)
                          .getAsInt();  // or throw
        return pos;
    }

    public static Location randomLocation(ProblemSet problem) {
        double[] loc = new double[problem.getProblemDimension()];
        double[] r1 = RandomGenerator.getInstance().nextDoubles(problem.getProblemDimension());
        IntStream.range(0, problem.getProblemDimension())
                .forEach(i -> {
                    loc[i] = problem.getMinimumLocation()[i] + r1[i] * (problem.getMaximumLocation()[i] - problem.getMinimumLocation()[i]);
                });

        return new Location(loc);
    }

    public static Velocity randomVelocity(ProblemSet problem) {
        double[] vel = new double[problem.getProblemDimension()];
        double[] r = RandomGenerator.getInstance().nextDoubles(problem.getProblemDimension());
        IntStream.range(0, problem.getProblemDimension())
                .forEach(i -> {
                    vel[i] = problem.getMinimumVelocity() + r[i] * (problem.getMaximumVelocity() - problem.getMinimumVelocity());
                });

        return new Velocity(vel);
    }

    public static Locale getLocale(String country) {
        return Arrays.stream(Locale.getAvailableLocales())
                .filter(l -> country.equalsIgnoreCase(l.getCountry()))
                .findFirst()
                .orElse(Locale.US);
    }

    public static double square(double x) {
        return x * x;
    }
}

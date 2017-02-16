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
        IntStream.range(0, problem.getProblemDimension())
                .forEach(j -> {
                    loc[j] = problem.getLocationMinimum()[j]
                                     + RandomGenerator.getInstance().getRandom().nextDouble()
                                               * (problem.getLocationMaximum()[j] - problem.getLocationMinimum()[j]);
                });

        return new Location(loc);
    }

    public static Velocity randomVelocity(ProblemSet problem) {
        double[] vel = new double[problem.getProblemDimension()];
        IntStream.range(0, problem.getProblemDimension())
                .forEach(j -> {
                    vel[j] = problem.getMinimumVelocity()
                                     + RandomGenerator.getInstance().getRandom().nextDouble()
                                               * (problem.getMaximumVelocity() - problem.getMinimumVelocity());
                });

        return new Velocity(vel);
    }

    public static Locale getLocale(String country) {
        return Arrays.stream(Locale.getAvailableLocales())
                .filter(l -> country.equalsIgnoreCase(l.getCountry()))
                .findFirst()
                .orElse(Locale.US);
    }
}

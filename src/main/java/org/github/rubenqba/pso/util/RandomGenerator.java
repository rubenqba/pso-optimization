package org.github.rubenqba.pso.util;

import java.security.SecureRandom;
import java.util.Random;
import java.util.stream.IntStream;

public class RandomGenerator {
    private static RandomGenerator instance;

    private Random random;

    private RandomGenerator() {
        random = new SecureRandom(SecureRandom.getSeed(55));
    }

    public static RandomGenerator getInstance() {
        if (instance == null)
            instance = new RandomGenerator();
        return instance;
    }

    public double nextDouble() {
        return random.nextDouble();
    }

    public double[] nextDoubles(int size) {
        double[] r = new double[size];

        IntStream.range(0, size)
                .forEach(i -> r[i] = nextDouble());

        return r;
    }
}
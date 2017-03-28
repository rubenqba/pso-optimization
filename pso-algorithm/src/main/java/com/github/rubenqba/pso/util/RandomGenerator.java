package com.github.rubenqba.pso.util;

import java.security.SecureRandom;
import java.util.Random;

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
        double[] r = random
                .doubles(size)
                .boxed()
                .mapToDouble(Double::doubleValue)
                .toArray();
        return r;
    }
}
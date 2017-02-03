package com.github.rubenqba.phd.pso.utils;

import lombok.Getter;

import java.util.Random;

public  class RandomGenerator {
    private static RandomGenerator instance;

    @Getter
    private Random random;

    private RandomGenerator() {
        random = new Random(0);
    }

    public static RandomGenerator getInstance() {
        if (instance == null)
            instance = new RandomGenerator();
        return instance;
    }
}
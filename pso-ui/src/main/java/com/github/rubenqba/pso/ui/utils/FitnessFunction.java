package com.github.rubenqba.pso.ui.utils;

import org.apache.commons.lang3.text.WordUtils;

public enum FitnessFunction {
    SPHERE,
    ROSENBROCK,
    GRIEWANK,
    RASTRIGIN,
    SCHAFFER6,
    GOLDSTEIN_PRICE;

    public String toString() {
        return WordUtils.capitalizeFully(super.toString().replace('_', ' '));
    }
}
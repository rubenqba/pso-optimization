package com.github.rubenqba.pso.problem;

import com.github.rubenqba.pso.util.PSOUtility;

/**
 * Created by ruben.bresler on 24/02/17.
 */
public class GoldsteinPriceProblem extends CommonProblemSet {

    @Override
    public double getOptimum() {
        return 3d;
    }

    @Override
    public double evaluate(double[] v) {
        double x1 = v[0];
        double x2 = v[1];

        double fact1a = PSOUtility.square(x1 + x2 + 1);
        double fact1b = 19 - 14*x1 + 3*x1*x1 - 14*x2 + 6*x1*x2 + 3*x2*x2;
        double fact1 = 1 + fact1a*fact1b;

        double fact2a = PSOUtility.square(2*x1 - 3*x2);
        double fact2b = 18 - 32*x1 + 12*x1*x1 + 48*x2 - 36*x1*x2 + 27*x2*x2;
        double fact2 = 30 + fact2a*fact2b;

        return fact1*fact2;
    }
}

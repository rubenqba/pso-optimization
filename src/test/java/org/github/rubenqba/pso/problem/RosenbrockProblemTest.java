package org.github.rubenqba.pso.problem;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by ruben.bressler on 15/02/17.
 */
public class RosenbrockProblemTest {

    @Test
    public void evaluate() throws Exception {

        RosenbrockProblem p = new RosenbrockProblem();

        double[] x1 = {1, 1, 1, 1, 1};
        double[] x2 = {1, 2, 3, 4, 5};

        assertThat(p.evaluate(x1), equalTo(0d));
        assertThat(p.evaluate(x2), equalTo(14814d));
    }

}
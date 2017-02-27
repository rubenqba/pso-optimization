package org.github.rubenqba.pso.problem;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by ruben.bresler on 24/02/17.
 */
public class GoldsteinPriceProblemTest extends ProblemTest {

    @Before
    public void setUp() {
        p = new GoldsteinPriceProblem();
    }

    @Test
    public void evaluate() throws Exception {
        double[] x1 = {0, -1};
        double[] x2 = {1, 2};

        assertThat(p.evaluate(x1), equalTo(3d));
        assertThat(p.evaluate(x2), equalTo(137150d));
    }

}
package org.github.rubenqba.pso.problem;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

/**
 * Created by ruben.bressler on 15/02/17.
 */
public class Schaffer6ProblemTest extends ProblemTest {

    @Before
    public void setUp() {
        p = new Schaffer6Problem();
    }

    @Test
    public void evaluate() throws Exception {

        double[] x1 = {0, 0};
        double[] x2 = {1, 2};

        assertThat(p.evaluate(x1), equalTo(0d));
        assertThat(p.evaluate(x2), closeTo(0.6177933, 1E-7));
    }

}
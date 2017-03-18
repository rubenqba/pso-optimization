package com.github.rubenqba.pso.problem;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

/**
 * Created by ruben.bressler on 15/02/17.
 */
public class GriewankProblemTest extends ProblemTest {

    @Before
    public void setUp() {
        p = new GriewankProblem();
    }

    @Test
    public void evaluate() throws Exception {

        double[] x1 = {0, 0, 0, 0, 0};
        double[] x2 = {1, 2, 3, 4, 5};

        assertThat(p.evaluate(x1), equalTo(0d));
        assertThat(p.evaluate(x2), closeTo(1.017225, 1E-6));
    }

}
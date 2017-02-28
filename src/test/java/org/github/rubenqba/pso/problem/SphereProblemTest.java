package org.github.rubenqba.pso.problem;

import org.github.rubenqba.pso.RK2Movement;
import org.github.rubenqba.pso.StandardMovement;
import org.github.rubenqba.pso.Swarm;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by ruben.bressler on 15/02/17.
 */
public class SphereProblemTest extends ProblemTest {

    @Before
    public void setUp() {
        p = new SphereProblem();
    }

    @Test
    public void evaluate() throws Exception {
        double[] x1 = {0, 0, 0, 0, 0};
        double[] x2 = {1, 2, 3, 4, 5};

        assertThat(p.evaluate(x1), equalTo(0d));
        assertThat(p.evaluate(x2), equalTo(55d));
    }

    @Test
    @Ignore
    public void testManual() {

        swarm = new Swarm();

        Arrays.asList(50, 100, 200, 500).stream()
                .forEach(s -> {
                    Arrays.asList(new StandardMovement(), new RK2Movement(0.5))
                            .stream()
                            .forEach(m -> {
                                swarm.setMovement(m);
                                p.setSwarmSize(s);
                                swarm.execute(p);
                            });
                });

    }

}
package com.github.rubenqba.pso.problem;

import com.github.rubenqba.pso.Swarm;
import com.github.rubenqba.pso.movement.ImplicitTrapezeMovement;
import com.github.rubenqba.pso.movement.RK2Movement;
import com.github.rubenqba.pso.movement.StandardMovement;
import com.github.rubenqba.pso.util.PSOUtility;
import com.opencsv.CSVWriter;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
    public void testManual() throws IOException {

        swarm = new Swarm();

        SphereProblem p1 = new SphereProblem() {
            @Override
            public double getW() {
                return 0.6;
            }

            @Override
            public double getWDamp() {
                return 0.99;
            }

            @Override
            public double getC1() {
                return 1.7;
            }

            @Override
            public double getC2() {
                return 1.7;
            }

            @Override
            public int getProblemDimension() {
                return 2;
            }

            @Override
            public double getErrorTolerance() {
                return 1E-2;
            }

            @Override
            public int getMaximumIterations() {
                return 1000;
            }

            @Override
            public String getName() {
                return "SphereModifiedProblem";
            }
        };

        CSVWriter writer = new CSVWriter(new FileWriter("target/" + p1.getName() + ".csv"));
        writer.writeNext(new String[]{"Movement", "Particles", "Iteration", "Value", "Error"});

        NumberFormat nf = NumberFormat.getInstance(PSOUtility.getLocale("es"));
        nf.setMaximumFractionDigits(8);

        Stream.of(50/*, 100, 200, 500*/)
                .forEach(s ->
                    Stream.of(new StandardMovement(), new RK2Movement(.5), new ImplicitTrapezeMovement())
                            .forEach(m -> {
                                List<Double> bestValue = new ArrayList<>();
                                swarm.setMovement(m);
                                p1.setSwarmSize(s);
                                swarm.execute(p1, bestValue);

                                IntStream.range(0, bestValue.size())
                                        .forEach(i -> writer.writeNext(new String[]{m.getName(), s.toString(), Integer.toString(i),
                                                nf.format(bestValue.get(i)),
                                                nf.format(Math.abs(p.getOptimum()-bestValue.get(i)))}));
                            })
                );
        writer.close();
    }

}
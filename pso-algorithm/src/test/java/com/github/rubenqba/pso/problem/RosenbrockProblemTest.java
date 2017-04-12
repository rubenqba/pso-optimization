package com.github.rubenqba.pso.problem;

import com.github.rubenqba.pso.Swarm;
import com.github.rubenqba.pso.movement.BDF2Movement;
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
public class RosenbrockProblemTest extends ProblemTest {

    @Before
    public void setUp() {
        p = new RosenbrockProblem();
    }

    @Test
    public void evaluate() throws Exception {

        double[] x1 = {1, 1, 1, 1, 1};
        double[] x2 = {1, 2, 3, 4, 5};

        assertThat(p.evaluate(x1), equalTo(0d));
        assertThat(p.evaluate(x2), equalTo(14814d));
    }

    @Test
    @Ignore
    public void testManual() throws IOException {

        swarm = new Swarm();

        RosenbrockProblem p1 = new RosenbrockProblem() {
            @Override
            public int getProblemDimension() {
                return 2;
            }

            @Override
            public double getErrorTolerance() {
                return 1E-10;
            }

            @Override
            public int getMaximumIterations() {
                return 5000;
            }

            @Override
            public String getName() {
                return "RosenbrockModifiedProblem";
            }

            @Override
            public StopCondition getStopCondition() {
                return StopCondition.ERROR;
            }
        };

        CSVWriter writer = new CSVWriter(new FileWriter("target/" + p1.getName() + ".csv"));
        writer.writeNext(new String[]{"Movement", "Particles", "Iteration", "Value", "Error"});

        NumberFormat nf = NumberFormat.getInstance(PSOUtility.getLocale("es"));
        nf.setMaximumFractionDigits(8);

        Stream.of(50, 100, 200, 500)
                .forEach(s ->
                        Stream.of(new StandardMovement(), new BDF2Movement())
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
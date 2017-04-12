package com.github.rubenqba.pso.problem;

import com.github.rubenqba.pso.Swarm;
import com.github.rubenqba.pso.movement.BDF2Movement;
import com.github.rubenqba.pso.movement.ImplicitTrapezoidalMovement;
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

        CSVWriter writer = new CSVWriter(new FileWriter("target/" + p.getName() + ".csv"));
        writer.writeNext(new String[]{"Movement", "Particles", "Iteration", "Value", "Error"});

        NumberFormat nf = NumberFormat.getInstance(PSOUtility.getLocale("es"));
        nf.setMaximumFractionDigits(8);

        Stream.of(10, 30, 50, 100)
                .forEach(s ->
                    Stream.of(new StandardMovement(), new ImplicitTrapezoidalMovement(.5), new BDF2Movement())
                            .forEach(m -> {
                                List<Double> bestValue = new ArrayList<>();
                                swarm.setMovement(m);
                                p.setSwarmSize(s);
                                swarm.execute(p, bestValue);

                                IntStream.range(0, bestValue.size())
                                        .forEach(i -> writer.writeNext(new String[]{m.getName(), s.toString(), Integer.toString(i),
                                                nf.format(bestValue.get(i)),
                                                nf.format(Math.abs(p.getOptimum()-bestValue.get(i)))}));
                            })
                );
        writer.close();
    }

}
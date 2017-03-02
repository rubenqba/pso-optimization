package org.github.rubenqba.pso.problem;

import com.opencsv.CSVWriter;
import org.github.rubenqba.pso.RK2Movement;
import org.github.rubenqba.pso.StandardMovement;
import org.github.rubenqba.pso.Swarm;
import org.github.rubenqba.pso.data.Location;
import org.github.rubenqba.pso.util.PSOUtility;
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
            public int getProblemDimension() {
                return 1;
            }

            @Override
            public double getErrorTolerance() {
                return 1E-10;
            }

            @Override
            public String getName() {
                return "SphereModifiedProblem";
            }
        };

        CSVWriter writer = new CSVWriter(new FileWriter("target/" + p1.getName() + ".csv"));
        writer.writeNext(new String[]{"Movement", "Particles", "Iteration", "L1", "Value", "Error"});

        NumberFormat nf = NumberFormat.getInstance(PSOUtility.getLocale("es"));
        nf.setMaximumFractionDigits(8);

        Stream.of(50, 100, 200, 500)
                .forEach(s ->
                    Stream.of(new StandardMovement(), new RK2Movement(0.5))
                            .forEach(m -> {
                                List<Location> bestPath = new ArrayList<>();
                                swarm.setMovement(m);
                                p1.setSwarmSize(s);
                                swarm.execute(p1, bestPath);

                                IntStream.range(0, bestPath.size())
                                        .forEach(i -> writer.writeNext(new String[]{m.getName(), s.toString(), Integer.toString(i),
                                                nf.format(bestPath.get(i).getLoc()[0]),
                                                nf.format(p1.evaluate(bestPath.get(i).getLoc())),
                                                nf.format(Math.abs(p.getOptimum()-p1.evaluate(bestPath.get(i).getLoc())))}));
                            })
                );
        writer.close();
    }

}
package org.github.rubenqba.pso.problem;

import com.opencsv.CSVWriter;
import org.github.rubenqba.pso.RK2Movement;
import org.github.rubenqba.pso.StandardMovement;
import org.github.rubenqba.pso.Swarm;
import org.github.rubenqba.pso.util.PSOUtility;
import org.junit.Before;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Created by ruben.bressler on 15/02/17.
 */
public abstract class ProblemTest {

    protected ProblemSet p;
    protected Swarm swarm;

    private int[] swarmSize = {15, 30, 60};

    @Before
    public abstract void setUp();


    @Test
    public void testProblem() throws IOException {

        NumberFormat nf = NumberFormat.getInstance(PSOUtility.getLocale("es"));
        nf.setMaximumFractionDigits(8);

        swarm = new Swarm();
        CSVWriter writer = new CSVWriter(new FileWriter("target/" + p.getName() + ".csv"));

        writer.writeNext(new String[]{"Movement", "Run", "Particles", "W", "C1", "C2", "Goal", "Iteration", "Value",
                "Error"});

        Arrays.asList(new StandardMovement(), new RK2Movement(.5), new RK2Movement(.25), new RK2Movement(1.5)).stream()
                .forEach(m -> {
                    swarm.setMovement(m);
                    IntStream.range(0, 5)
                            .forEach(i ->
                                    Arrays.stream(swarmSize)
                                            .forEach(s -> {
                                                p.setSwarmSize(s);
                                                swarm.execute(p);
                                                writer.writeNext(new String[]{swarm.getMovement().getName(), nf.format(i), nf.format
                                                        (p.getSwarmSize()),
                                                        nf.format(p.getW()), nf.format(p.getC1()), nf.format(p.getC2()),
                                                        nf.format(p.getErrorTolerance()),
                                                        Integer.toString(swarm.getIteration()),
                                                        nf.format(swarm.getBestFitness()), nf.format(swarm.getError())});
                                            })
                            );
                });
        writer.close();
    }
}

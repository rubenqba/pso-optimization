package org.github.rubenqba.pso.problem;

import com.opencsv.CSVWriter;
import org.github.rubenqba.pso.Swarm;
import org.github.rubenqba.pso.util.PSOUtility;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.stream.IntStream;

/**
 * Created by ruben.bressler on 15/02/17.
 */
public class ProblemTest {

    protected PSOProblemSet p;
    protected Swarm swarm;

    @Test
    public void testProblem() throws IOException {

        NumberFormat nf = NumberFormat.getInstance(PSOUtility.getLocale("es"));
        nf.setMaximumFractionDigits(8);

        swarm = new Swarm();

        CSVWriter writer = new CSVWriter(new FileWriter("target/" + p.getName() + ".csv"));
        writer.writeNext(new String[]{"Function", "Run", "Particles", "W", "C1", "C2", "Goal", "Iteration", "Value",
                "Error"});
        IntStream.range(0, 20)
                .forEach(i -> {
                    swarm.execute(p);
                    writer.writeNext(new String[]{p.getName(), nf.format(i), nf.format(p.getSwarmSize()), nf.format(p.getW(0)),
                            nf.format(p.getC1()), nf.format(p.getC2()), nf.format(p.getErrorTolerance()), nf.format(swarm.getIteration()),
                            nf.format(swarm.getGBest()), nf.format(swarm.getError())});
                });

        writer.close();
    }
}

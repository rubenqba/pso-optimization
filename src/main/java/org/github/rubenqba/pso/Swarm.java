package org.github.rubenqba.pso;

import com.opencsv.CSVWriter;
import lombok.Getter;
import lombok.Setter;
import org.github.rubenqba.pso.data.Location;
import org.github.rubenqba.pso.data.Velocity;
import org.github.rubenqba.pso.problem.ProblemSet;
import org.github.rubenqba.pso.util.PSOUtility;
import org.github.rubenqba.pso.util.RandomGenerator;

import java.io.IOException;
import java.io.StringWriter;
import java.text.NumberFormat;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingDouble;

@Getter
public class Swarm {

    private ProblemSet problem;
    private List<Particle> swarm;
    private double gBest;
    private Location gBestLocation;

    private int iteration;
    @Setter
    private boolean debug = false;

    public void execute(ProblemSet problem) {
        this.problem = problem;

        gBest = Double.MAX_VALUE;

        initializeSwarm();

        iteration = 0;
        while (getError() > problem.getErrorTolerance() && iteration < problem.getMaximumIterations()) {
            updateFitnessList();
            iterate();
        }

        summary(this);
    }

    private void summary(Swarm s) {
        NumberFormat nf = NumberFormat.getInstance(PSOUtility.getLocale("mx"));
        nf.setMaximumFractionDigits(8);

        System.out.println(String.format("\nSolution of %s %sfound at iteration %d, the solutions is:",
                s.getProblem().getName(), (s.getError() > problem.getErrorTolerance()) ? "not-" : "", s.getIteration()));
        System.out.println("     Best : " + s.getGBestLocation());
        System.out.println("     Value: " + nf.format(s.getGBest()));
        if (problem.getOptimum() != 0)
            System.out.println("     Error: " + nf.format(s.getError()));
    }

    public void iterate() {
        updateFitnessList();

        final double w = problem.getW(iteration);

        Random generator = RandomGenerator.getInstance().getRandom();

        swarm.stream().forEach(p -> {
            double[] r1 = RandomGenerator.getInstance().nextDoubles(problem.getProblemDimension());
            double[] r2 = RandomGenerator.getInstance().nextDoubles(problem.getProblemDimension());

            // step 3 - update velocity
            // step 4 - update location
            double[] newVel = new double[problem.getProblemDimension()];
            double[] newLoc = new double[problem.getProblemDimension()];

            IntStream.range(0, problem.getProblemDimension())
                    .forEach(j -> {
                        newVel[j] = (w * p.getVelocity().getVelocity()[j]) +
                                (r1[j] * problem.getC1()) * (p.getBestLocation().getLoc()[j] - p.getLocation
                                                                                                                  ()
                                                                                                                .getLoc()[j]) +
                                (r2[j] * problem.getC2()) * (gBestLocation.getLoc()[j] - p.getLocation()
                                                                                                          .getLoc()[j]);
                        newLoc[j] = p.getLocation().getLoc()[j] + newVel[j];
                    });
            p.setVelocity(new Velocity(newVel));
            p.setLocation(new Location(newLoc));
        });

        if (isDebug()) {
            NumberFormat nf = NumberFormat.getInstance(PSOUtility.getLocale("mx"));
            System.out.println("ITERATION " + (iteration + 1) + ": ");
            System.out.println("     Best : " + gBestLocation);
            System.out.println("     Value: " + nf.format(problem.evaluate(gBestLocation.getLoc())));
            System.out.println("     Error: " + getError());
        }

        iteration++;
    }

    public void initializeSwarm() {
        swarm = IntStream.range(0, problem.getSwarmSize())
                        .mapToObj(i -> {
                            Particle p = new Particle();
                            p.setLocation(PSOUtility.randomLocation(problem));
                            p.setVelocity(PSOUtility.randomVelocity(problem));
                            return p;
                        }).collect(Collectors.toList());
    }

    public void updateFitnessList() {
        List<Double> fitness = swarm.stream()
                                       .map(p -> p.getFitnessValue(problem))
                                       .collect(Collectors.toList());

        int best = IntStream.range(0, swarm.size()).boxed()
                           .min(comparingDouble(fitness::get))
                           .get();

        if (gBest > fitness.get(best)) {
            gBest = fitness.get(best);
            gBestLocation = new Location(swarm.get(best).getLocation());
        }
    }

    public double getError() {
        // minimizing the functions means it's getting closer to problem optimum
        return Math.abs(gBest - problem.getOptimum());
    }

    protected void exportToCsv() {
        StringWriter sw = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(sw);

        swarm.stream()
                .map(Particle::toCsv)
                .forEach(csvWriter::writeNext);

        try {
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(sw);
    }
}
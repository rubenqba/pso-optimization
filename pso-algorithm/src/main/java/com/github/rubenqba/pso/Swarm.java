package com.github.rubenqba.pso;

import com.github.rubenqba.pso.data.Location;
import com.github.rubenqba.pso.movement.StandardMovement;
import com.github.rubenqba.pso.problem.CommonProblemSet;
import com.github.rubenqba.pso.problem.StopCondition;
import com.github.rubenqba.pso.util.PSOUtility;
import com.opencsv.CSVWriter;

import java.io.IOException;
import java.io.StringWriter;
import java.text.NumberFormat;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingDouble;

public class Swarm {

    protected Movement movement = new StandardMovement();
    protected CommonProblemSet problem;
    protected List<Particle> swarm;
    protected double gBest;
    protected Location gBestLocation;

    protected int iteration;
    protected boolean debug = false;

    public void execute(CommonProblemSet problem, List<Double> history) {
        this.problem = problem;
        gBest = Double.MAX_VALUE;

        initializeSwarm();
        iteration = 0;

        Predicate<StopCondition> stopCondition = (condition -> {
            switch (condition) {
                case BOTH:
                    return getError() > problem.getErrorTolerance() && iteration < problem.getMaximumIterations();
                case ERROR:
                    return getError() > problem.getErrorTolerance();
                case ITERATIONS:
                    return iteration < problem.getMaximumIterations();
                default:
                    return false;
            }
        });


        while (stopCondition.test(problem.getStopCondition())) {
            updateFitnessList();
            iterate();

            if (history != null) {
                history.add(getBestFitness());
            }
        }

        summary(this);
    }

    private void summary(Swarm s) {
        NumberFormat nf = NumberFormat.getInstance(PSOUtility.getLocale("mx"));
        nf.setMaximumFractionDigits(8);

        System.out.println(String.format("\nSolution of %s-%s %sfound at iteration %d, the solutions is:",
                s.getMovement().getName(), s.getProblem().getName(),
                (s.getError() > problem.getErrorTolerance()) ? "not-" : "", s.getIteration()));
        System.out.println("     Particles: " + problem.getSwarmSize());
        System.out.println("     Best     : " + s.getBestLocation());
        System.out.println("     Value    : " + nf.format(s.getBestFitness()));
        if (problem.getOptimum() != 0)
            System.out.println("     Error: " + nf.format(s.getError()));
    }

    public void iterate() {

        updateFitnessList();

        swarm.stream().forEach(p -> movement.moveParticle(this, p));

        iteration++;
    }


    public void initializeSwarm() {
        swarm = IntStream.range(0, problem.getSwarmSize())
                .mapToObj(i -> {
                    Particle p = new Particle();
                    p.setLocation(PSOUtility.randomLocation(problem));
                    p.setVelocity(PSOUtility.fixedVelocity(problem, 0));
                    return p;
                }).collect(Collectors.toList());
    }

    protected void updateFitnessList() {
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
        // minimizing the functions means it's getting closer to com.github.rubenqba.pso.problem optimum
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

    public double getBestFitness() {
        return gBest;
    }

    public Location getBestLocation() {
        return gBestLocation;
    }

    public CommonProblemSet getProblem() {
        return problem;
    }

    public List<Particle> getSwarm() {
        return swarm;
    }

    public int getIteration() {
        return iteration;
    }

    public boolean isDebug() {
        return debug;
    }

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }
}
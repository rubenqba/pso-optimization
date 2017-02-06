package com.github.rubenqba.phd.pso.utils;

import com.github.rubenqba.phd.pso.PSOUtility;
import com.github.rubenqba.phd.pso.core.PSOProblemSet;
import lombok.Getter;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingDouble;

public class Swarm {

    @Getter
    private PSOProblemSet problem;

    @Getter
    private List<Particle> swarm;

    @Getter
    private double bestFitness;
    @Getter
    private Location bestLocation;

    @Getter
    private int iteration;

    private Random generator;

    public Swarm(PSOProblemSet problem) {
        this.problem = problem;
        this.generator = RandomGenerator.getInstance().getRandom();
        this.iteration = 0;
        this.bestFitness = Double.MAX_VALUE;
    }

    public void initialize() {
        swarm = IntStream.range(0, problem.getSwarmSize())
                .mapToObj(i ->{
                    Particle p = new Particle();
                    p.setLocation(PSOUtility.randomLocation(problem));
                    p.setVelocity(PSOUtility.randomVelocity(problem));
                    return p;
                }).collect(Collectors.toList());
    }

    public void updateFitness() {
        List<Double> fitness = swarm.stream()
                .map(p -> p.getFitnessValue(problem))
                .collect(Collectors.toList());

        int best = IntStream.range(0,swarm.size()).boxed()
                .min(comparingDouble(fitness::get))
                .get();

        if (bestFitness > fitness.get(best)) {
            bestFitness = fitness.get(best);
            bestLocation = swarm.get(best).getLocation().clone();
        }
    }

    public void iterate() {

        updateFitness();

        swarm.stream().forEach(p -> {
            double r1 = generator.nextDouble();
            double r2 = generator.nextDouble();

            Velocity v1 = p.getVelocity().clone();
            v1.multiplicarEscalar(problem.getW(iteration));

            Location l1 = new Location(Vector.substract(p.getBestLocation(), p.getLocation()).getDatos());
            l1.multiplicarEscalar(r1 * problem.getC1());

            Location l2 = new Location(Vector.substract(bestLocation, p.getLocation()).getDatos());
            l2.multiplicarEscalar(r2 * problem.getC2());

            Velocity vel = new Velocity(Vector.sumUp(v1, l1, l2).getDatos());
            p.setVelocity(vel);

            Location loc = p.getLocation().clone();
            loc.sumar(vel);

            // step 4 - update location
            p.setLocation(loc);
        });

        iteration++;
    }

    public void optimize() {

        iteration = 0;
        double tolerance = problem.getErrorTolerance();
        int maxIterations = problem.getMaximumIterations();

        while(iteration < maxIterations && tolerance < getError()) {
            iterate();
        }
    }

    public double getError() {
        // minimizing the functions means it's getting closer to problem optimum
        return bestFitness - problem.getOptimum();
    }


}

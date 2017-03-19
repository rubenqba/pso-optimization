package com.github.rubenqba.pso;

import com.github.rubenqba.pso.data.Location;
import com.github.rubenqba.pso.data.Velocity;
import com.github.rubenqba.pso.problem.CommonProblemSet;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class Particle {
    @CsvBindByName
    private double fitnessValue;
    @CsvBindByName
    private double bestFitness;
    @CsvBindByName
    private Location bestLocation;

    @Setter
    @CsvBindByName
    private Velocity velocity;
    @Setter
    @CsvBindByName
    private Location location;
    @Setter
    private Location before;

    public Particle() {
        super();
        bestFitness = Double.MAX_VALUE;
    }

    public double getFitnessValue(CommonProblemSet problem) {
        fitnessValue = problem.evaluate(location.getLoc());

        if (fitnessValue < bestFitness) {
            bestFitness = fitnessValue;
            bestLocation = new Location(location);
        }
        return fitnessValue;
    }

    public String[] toCsv() {
        List<String> data = new ArrayList<>();
        data.add(Double.toString(fitnessValue));
        data.add(Double.toString(bestFitness));
        Arrays.stream(velocity.getVelocity())
                .mapToObj(Double::toString)
                .forEach(data::add);

        return data.toArray(new String[]{});
    }
}

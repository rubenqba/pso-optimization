package org.github.rubenqba.pso;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import org.github.rubenqba.pso.data.Location;
import org.github.rubenqba.pso.data.Velocity;
import org.github.rubenqba.pso.problem.PSOProblemSet;

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

    public Particle() {
        super();
        bestFitness = Double.MAX_VALUE;
    }

    public double getFitnessValue(PSOProblemSet problem) {
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

package com.github.rubenqba.phd.pso.utils;

/**
 * Created by mlpr2 on 31/01/17.
 */
public class Location extends Vector {

    public Location(double[] data) {
        super(data);
    }

    public Location clonar() {
        return new Location(this.getDatos());
    }
}

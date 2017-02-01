package com.github.rubenqba.phd.pso.utils;

import lombok.NoArgsConstructor;

/**
 * Created by mlpr2 on 31/01/17.
 */
@NoArgsConstructor
public class Velocity extends Vector {

    public Velocity(double[] data) {
        super(data);
    }

    public Velocity clonar() {
        return new Velocity(this.getDatos());
    }
}

package com.github.rubenqba.pso.data;

public class Velocity {
    // store the Velocity in an array to accommodate multi-dimensional com.github.rubenqba.pso.problem space
    private double[] vel;

    public Velocity(double[] vel) {
        super();
        this.vel = vel;
    }

    public double[] getVelocity() {
        return vel;
    }

    public void setVelocity(double[] vel) {
        this.vel = vel;
    }

}
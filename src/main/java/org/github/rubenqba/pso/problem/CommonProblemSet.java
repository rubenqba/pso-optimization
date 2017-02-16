package org.github.rubenqba.pso.problem;

/**
 * Created by ruben.bressler on 15/02/17.
 */
abstract class CommonProblemSet implements ProblemSet {
    private int swarmSize = 30;

    @Override
    public int getSwarmSize() {
        return swarmSize;
    }

    @Override
    public void setSwarmSize(int swarmSize) {
        this.swarmSize = swarmSize;
    }
}

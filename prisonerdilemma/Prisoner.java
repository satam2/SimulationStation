package prisonerdilemma;
import simstation.*;

public class Prisoner extends Agent {
    private int fitness = 0;
    private boolean cheated = false;
    private Strategy strategy;

    public Prisoner(Simulation world, Strategy strategy) {
        super(world);
        this.strategy = strategy;
    }

    public int getFitness() {
        return fitness;
    }

    public boolean hasCheated() {
        return cheated;
    }

    @Override
    public void update() {
        Agent neighbor = world.getNeighbor(this, 20);
        if (neighbor != null && neighbor != this) {
            Prisoner otherPrisoner = (Prisoner) neighbor;
            boolean myCooperate = strategy.cooperate();
            boolean otherCooperate = otherPrisoner.strategy.cooperate();

            if (myCooperate && otherCooperate) {
                updateFitness(3);
                otherPrisoner.updateFitness(3);
            } else if (myCooperate && !otherCooperate) {
                updateFitness(0);
                otherPrisoner.updateFitness(5);
            } else if (!myCooperate && otherCooperate) {
                updateFitness(5);
                otherPrisoner.updateFitness(0);
            } else { // both cheat
                updateFitness(1);
                otherPrisoner.updateFitness(1);
            }

            cheated = !myCooperate && otherCooperate;
            otherPrisoner.cheated = !otherCooperate && myCooperate;
        }
    }

    private void updateFitness(int amount) {
        fitness += amount;
    }

    public Strategy getStrategy() {
        return strategy;
    }
}

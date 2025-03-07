package plague;
import mvc.*;
import simstation.*;

public class Organism extends Agent {
    private boolean infected, resistant;

    public Organism(Simulation world){
        super(world);
        resistant = Utilities.rng.nextInt(100) < PlagueSimulation.RESISTANCE;
        infected = !resistant && Utilities.rng.nextInt(100) < PlagueSimulation.VIRULENCE;
    }

    public boolean isInfected() {
        return infected;
    }

    public boolean isResistant(){
        return resistant;
    }

    public void setInfected(){
        if (!resistant)
            infected = true;
    }

    @Override
    public void update() {
        heading = Heading.random();
        int steps = Utilities.rng.nextInt(10) + 1;
        move(steps);

        if (infected) {
            // Check nearby agents and try to infect them
            Agent neighbor = world.getNeighbor(this,25);
            if (neighbor != this && neighbor != null) {
                Organism orgNeighbor = (Organism)neighbor;
                if(!orgNeighbor.isInfected())
                    if(Utilities.rng.nextInt(100)< PlagueSimulation.VIRULENCE)
                        orgNeighbor.setInfected();
            }
        }
    }

}

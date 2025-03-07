package flocking;

import mvc.Utilities;
import simstation.Agent;
import simstation.Simulation;
public class Bird extends Agent {
    private int speed;

    public Bird(Simulation world) {
        super(world);
        speed = Utilities.rng.nextInt(5) + 1;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void update() {
        Agent neighbor = world.getNeighbor(this, 20);
        if (neighbor != null && neighbor != this) {
            Bird birdNeighbor = (Bird) neighbor;
            this.speed = birdNeighbor.getSpeed();
            this.heading.setDirection(birdNeighbor.heading.getDirection());
        }
        move(speed); // Move with the current speed
    }
}


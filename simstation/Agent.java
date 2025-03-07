package simstation;

import mvc.Utilities;

import java.awt.*;
import java.io.Serializable;

public abstract class Agent implements Serializable, Runnable {
    private static final int DEFAULT_RADIUS = 10;
    private String name;
    public Heading heading;
    private Point coord;
    private int radius = DEFAULT_RADIUS;
    private boolean suspended, stopped;
    transient protected Thread myThread;
    public Simulation world;

    public Agent(Simulation world) {
        this.world = world;
        heading = new Heading();
        suspended = false;
        stopped = false;
        myThread = null;
        coord = new Point(Utilities.rng.nextInt(world.getWidth()), Utilities.rng.nextInt(world.getHeight()));
    }

    public int getRadius() {
        return radius;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point getCoord() {
        return coord;
    }

    @Override
    public void run() {
        myThread = Thread.currentThread();
        onStart();
        while (!stopped) {
            try {
                this.update();
                Thread.sleep(20);
                checkSuspended();
            } catch (InterruptedException e) {
                Utilities.error(e);
            }
        }
        onExit();
    }

    public abstract void update();

    public void start() {}

    private synchronized void checkSuspended() {
        try {
            while (!stopped && suspended) {
                wait();
                suspended = false;
            }
        } catch (InterruptedException e) {
            Utilities.error(e);
        }
    }

    public synchronized void suspend() {
        suspended = true;
        onInterrupted();
    }

    public synchronized boolean isSuspended() {
        return suspended;
    }

    public synchronized void resume() {
        notify();
    }

    public void stop() {
        stopped = true;
    }

    public synchronized boolean isStopped() {
        return stopped;
    }

    public void onStart() {}

    public void onInterrupted() {}

    public void onExit() {}

    public void setSimulation(Simulation world) {
        this.world = world;
    }

    public void move(int steps) {
        Point oldPoint = new Point(coord); // Create a copy of the current point
        int x = coord.x;
        int y = coord.y;
        switch (heading.getDirection()) {
            case Heading.NORTH:
                y -= steps;
                break;
            case Heading.SOUTH:
                y += steps;
                break;
            case Heading.EAST:
                x += steps;
                break;
            case Heading.WEST:
                x -= steps;
                break;
        }
        // Wrap around logic
        x = (x + world.getWidth()) % world.getWidth();
        y = (y + world.getHeight()) % world.getHeight();
        coord.setLocation(x, y);

        world.changed(oldPoint, coord); // Notify world of the change
    }

    public int getXc() {
        return coord.x;
    }

    public int getYc() {
        return coord.y;
    }
}

package simstation;

import mvc.Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Simulation extends Model {
    protected static final int READY_STATE = 0;
    private static final int RUNNING_STATE = 1;
    private static final int STOPPED_STATE = 2;
    protected static final int SUSPENDED_STATE = 3;

    transient private Timer timer;
    private ArrayList<Agent> agents;
    public int clock;
    private int state;
    private int width;
    private int height;

    public Simulation() {
        agents = new ArrayList<>();
        clock = 0;
        state = READY_STATE;
    }

    public ArrayList<Agent> getAgents() {
        return agents;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void start() {
        populate();
        startTimer();
        for (Agent a : agents) {
            Thread thread = new Thread(a);
            thread.start();
        }
        state = RUNNING_STATE;
    }

    public void suspend() {
        state = SUSPENDED_STATE;
        for (Agent a : agents) {
            a.suspend();
        }
    }

    public void resume() {
        state = RUNNING_STATE;
        for (Agent a : agents) {
            a.resume();
        }
    }

    public void stop() {
        state = READY_STATE;
        for (Agent a : agents) {
            a.stop();
        }
        agents.clear();
        changed();
    }

    public Agent getNeighbor(Agent a, double radius) {
        for (Agent agent : agents) {
            if (agent.getCoord().distance(a.getCoord()) <= radius) {
                return agent;
            }
        }
        return null;
    }

    public void stats() {
        // Implement later
    }

    public int getState() {
        return state;
    }

    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new ClockUpdater(), 1000, 1000);
    }

    private void stopTimer() {
        timer.cancel();
        timer.purge();
    }

    public void populate() {}

    public void addAgent(Agent a) {
        agents.add(a);
    }

    public synchronized void changed(Point oldPoint, Point newPoint) {
        changed();
    }

    private class ClockUpdater extends TimerTask {
        public void run() {
            clock++;
        }
    }
}

package flocking;

import mvc.AppPanel;
import simstation.Agent;
import simstation.Simulation;
import simstation.SimulationPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class FlockingSimulation extends Simulation {
    @Override
    public void populate() {
        for (int i = 0; i < 50; i++) {
            addAgent(new Bird(this));
        }
    }

    public void stats() {
        Map<Integer, Integer> speedCounts = new HashMap<>();
        for (Agent agent : getAgents()) {
            Bird bird = (Bird) agent;
            int speed = bird.getSpeed();
            speedCounts.put(speed, speedCounts.getOrDefault(speed, 0) + 1);
        }

        StringBuilder statsBuilder = new StringBuilder();
        for (Map.Entry<Integer, Integer> entry : speedCounts.entrySet()) {
            statsBuilder.append("#birds @ speed ").append(entry.getKey()).append("= ").append(entry.getValue()).append("\n");
        }

        JOptionPane.showMessageDialog(null, statsBuilder.toString(), "Bird Speed Stats", JOptionPane.INFORMATION_MESSAGE);
    }

    public String[] getStats() {
        Map<Integer, Integer> speedCounts = new HashMap<>();
        for (Agent agent : getAgents()) {
            Bird bird = (Bird) agent;
            int speed = bird.getSpeed();
            speedCounts.put(speed, speedCounts.getOrDefault(speed, 0) + 1);
        }

        ArrayList<String> statsList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : speedCounts.entrySet()) {
            statsList.add("#birds @ speed " + entry.getKey() + "= " + entry.getValue());
        }

        return statsList.toArray(new String[0]);
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new FlockingFactory());
        panel.display();
    }
}


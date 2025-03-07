package plague;
import mvc.*;
import simstation.*;
import javax.swing.*;

public class PlagueSimulation extends Simulation {
    public static int VIRULENCE = 50; // % chance of infection
    public static int RESISTANCE = 5; // % chance of being resistant

    public void populate() {
        for (int i = 0; i < 50; i++) {
            addAgent(new Organism(this));
        }
    }

    @Override
    public void stats() {
        int totalAgents = getAgents().size();
        int count = 0;
        int resAgents = 0;

        for (Agent agent : getAgents()) {
            Organism organism = (Organism) agent;
            if(organism.isResistant())
                resAgents++;
            if (organism.isInfected()) {
                count++;
            }
        }

        double percent = 0;
        if (totalAgents > 0) {
            percent = (double) count / totalAgents * 100; // Calculate age of infected agents
        }

        StringBuilder statsBuilder = new StringBuilder();
        statsBuilder.append("# agents = ").append(totalAgents).append("\n");
        statsBuilder.append("clock = ").append(this.clock).append("\n");
        statsBuilder.append("# agents immune = ").append(resAgents).append("\n");
        statsBuilder.append("% infected = ").append(String.format("%.2f%%", percent)).append("\n");

        JOptionPane.showMessageDialog(null, statsBuilder.toString(), "Infection Stats", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new PlagueFactory());
        panel.display();
    }
}

package plague;

import mvc.*;
import simstation.*;

import java.awt.*;
import java.util.List;

public class PlagueView extends SimulationView {
    private Simulation simulation;
    public PlagueView(Model model) {
        super(model);
        if (model instanceof PlagueSimulation) {
            this.simulation = (PlagueSimulation) model;
        } else {
            throw new IllegalArgumentException("Model must be an instance of PlagueSimulation");
        }
    }

    @Override
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        List<Agent> agents = simulation.getAgents();
        if(!agents.isEmpty()&&agents!=null) {
            for (Agent agent : agents) {
                if (agent instanceof Organism) {
                    Organism org = (Organism) agent;
                    if (org.isInfected())
                        gc.setColor(Color.RED);
                    else
                        gc.setColor(Color.GREEN);
                    gc.fillOval(org.getXc(), org.getYc(), 10, 10); // Draw a small oval as a dot
                    gc.drawOval(org.getXc(), org.getYc(), 10, 10); // make oval look cleaner
                }
            }
        }
    }
}

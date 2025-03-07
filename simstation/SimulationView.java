package simstation;

import mvc.Model;
import mvc.View;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class SimulationView extends View {
    public SimulationView(Model model) {
        super(model);
        setBackground(Color.gray);
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Simulation simulation = (Simulation)model;
        simulation.setWidth(getWidth());
        simulation.setHeight(getHeight());
        ArrayList<Agent> agentList = simulation.getAgents();

        Graphics2D g2 = (Graphics2D) gc;
        for(Agent a : agentList)
        {
            Ellipse2D oval = new Ellipse2D.Double(a.getCoord().x, a.getCoord().y, a.getRadius(), a.getRadius());
            g2.draw(oval);
            g2.setColor(Color.WHITE);
            g2.fill(oval);
        }
    }

}
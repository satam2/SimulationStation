package prisonerdilemma;

import mvc.*;
import simstation.*;
import javax.swing.*;

public class PrisonerSimulation extends Simulation {
    @Override
    public void populate() {
        int numPrisoners = 40; // Total number of prisoners
        int numEachStrategy = numPrisoners / 4; // Number of prisoners for each strategy

        // Populate with prisoners using different strategies
        for (int i = 0; i < numEachStrategy; i++) {
            addAgent(new Prisoner(this, new AlwaysCheat()));
            addAgent(new Prisoner(this, new AlwaysCooperate()));
            addAgent(new Prisoner(this, new RandomlyCooperate()));
            addAgent(new Prisoner(this, new Tit4Tat()));
        }
    }

    @Override
    public void stats() {
        int totalFitnessAlwaysCheat = 0;
        int totalFitnessAlwaysCooperate = 0;
        int totalFitnessRandomlyCooperate = 0;
        int totalFitnessTitForTat = 0;

        int numAlwaysCheat = 0;
        int numAlwaysCooperate = 0;
        int numRandomlyCooperate = 0;
        int numTitForTat = 0;

        for (simstation.Agent agent : getAgents()) {
            Prisoner prisoner = (Prisoner) agent;
            int fitness = prisoner.getFitness();
            Strategy strategy = prisoner.getStrategy();

            if (strategy instanceof AlwaysCheat) {
                totalFitnessAlwaysCheat += fitness;
                numAlwaysCheat++;
            } else if (strategy instanceof AlwaysCooperate) {
                totalFitnessAlwaysCooperate += fitness;
                numAlwaysCooperate++;
            } else if (strategy instanceof RandomlyCooperate) {
                totalFitnessRandomlyCooperate += fitness;
                numRandomlyCooperate++;
            } else if (strategy instanceof Tit4Tat) {
                totalFitnessTitForTat += fitness;
                numTitForTat++;
            }
        }

        int avgFitnessAlwaysCheat = numAlwaysCheat > 0 ? totalFitnessAlwaysCheat / numAlwaysCheat : 0;
        int avgFitnessAlwaysCooperate = numAlwaysCooperate > 0 ? totalFitnessAlwaysCooperate / numAlwaysCooperate : 0;
        int avgFitnessRandomlyCooperate = numRandomlyCooperate > 0 ? totalFitnessRandomlyCooperate / numRandomlyCooperate : 0;
        int avgFitnessTitForTat = numTitForTat > 0 ? totalFitnessTitForTat / numTitForTat : 0;

        String[] stats = {
                "Average Fitness - Always Cheat: " + avgFitnessAlwaysCheat,
                "Average Fitness - Always Cooperate: " + avgFitnessAlwaysCooperate,
                "Average Fitness - Randomly Cooperate: " + avgFitnessRandomlyCooperate,
                "Average Fitness - Tit For Tat: " + avgFitnessTitForTat
        };

        displayStats(stats);
    }

    private void displayStats(String[] stats) {
        StringBuilder statsBuilder = new StringBuilder();
        for (String stat : stats) {
            statsBuilder.append(stat).append("\n");
        }

        JOptionPane.showMessageDialog(null, statsBuilder.toString(), "Prisoner Fitness Stats", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new PrisonerFactory());
        panel.display();
    }
}




// package prisonerdilemma;
// import simstation.*;

// public class /**/PrisonerSimulation extends Simulation {
// }

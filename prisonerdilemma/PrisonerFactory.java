package prisonerdilemma;
import mvc.*;
import simstation.*;

public class /**/PrisonerFactory extends SimulationFactory {
    @Override
    public Model makeModel() {
        return new PrisonerSimulation();
    }

    @Override
    public String getTitle() {
        return "Prisoner's Dilemma";
    }
}

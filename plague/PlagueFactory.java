package plague;
import mvc.*;
import simstation.*;

public class PlagueFactory extends SimulationFactory {
    @Override
    public View makeView(Model m){
        return new PlagueView(m);
    }
    @Override
    public Model makeModel() {
        return new PlagueSimulation();
    }

    @Override
    public String getTitle() {
        return "Plague";
    }
}

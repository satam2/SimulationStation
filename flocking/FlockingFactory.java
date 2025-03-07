package flocking;
import mvc.Model;
import simstation.SimulationFactory;

class FlockingFactory extends SimulationFactory {
    public Model makeModel() {
        return new FlockingSimulation();
    }
    public String getTitle() {
        return "Flocking";
    }
}

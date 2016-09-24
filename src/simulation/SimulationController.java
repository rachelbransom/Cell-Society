package simulation;

import simulation.types.AbstractSimulation;

public class SimulationController {

	private AbstractSimulation mySimulation;
	private SimulationVisualizer myVisualizer;
	
	public void initializeSimulation(String filename){
		
		SimulationFactory sf = new SimulationFactory(filename);
		
<<<<<<< HEAD
		mySimulation = sf.makeSimulation();
>>>>>>> 35d3acb054cce2c9c163642064a600a8d2c8a364
		
		
	}
	
	
}

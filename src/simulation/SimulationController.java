package simulation;

import simulation.types.AbstractSimulation;

public class SimulationController {

	private AbstractSimulation mySimulation;
	private SimulationVisualizer myVisualizer;
	
	public void initializeSimulation(String filename){
		
		SimulationFactory sf = new SimulationFactory(filename);
		
		mySimulation = sf.makeSimulation();
		
		
	}
	
	
}

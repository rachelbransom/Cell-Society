package simulation;


public class SimulationController {

	private AbstractSimulation mySimulation;
	private SimulationVisualizer myVisualizer;
	
	public void initializeSimulation(String filename){
		
		SimulationFactory sf = new SimulationFactory();
		
		mySimulation = sf.makeSimulation( filename );
		
		
	}
	
}

package logic;


public class SimulationController {

	private Simulation mySimulation;
	
	
	public void initializeSimulation(String filename){
		
		SimulationFactory sf = new SimulationFactory();
		
		mySimulation = sf.makeSimulation( filename );
		
		
	}
	
}

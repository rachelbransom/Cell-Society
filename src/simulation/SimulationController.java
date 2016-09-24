package simulation;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import simulation.types.AbstractSimulation;

//@authour: Rachel Bransom

public class SimulationController {

	private AbstractSimulation mySimulation;
	private SimulationVisualizer myVisualizer;
	
	public void initializeSimulation(String filename){
		
		SimulationFactory sf = new SimulationFactory(filename);
		mySimulation = sf.makeSimulation();

	}
	
	public Group returnVisualGrid(){
		Color[][] colorGrid = mySimulation.showColorGrid();
		myVisualizer = new SimulationVisualizer(colorGrid.length);
		Group gridRoot = myVisualizer.returnVisualGrid(colorGrid);
		return gridRoot;
	}
	
}

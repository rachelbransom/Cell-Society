package simulation;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import simulation.types.AbstractSimulation;

//@authour: Rachel Bransom
//@author: Diane Hadley

public class SimulationController {

	private AbstractSimulation mySimulation;
	private SimulationVisualizer myVisualizer;
	
	public void initializeSimulation(String filename){		  
		SimulationFactory factory = new SimulationFactory(filename);
		mySimulation = factory.makeSimulation();
	}
	
	
	
	public Group returnCurrVisualGrid(){
		Color[][] colorGrid = mySimulation.showCurrColorGrid();
		myVisualizer = new SimulationVisualizer(colorGrid.length);
		Group gridRoot = myVisualizer.returnVisualGrid(colorGrid);
		return gridRoot;
	}
	
	public Group returnNextVisualGrid(){
		
		Color[][] colorGrid = mySimulation.showNextColorGrid();
		myVisualizer = new SimulationVisualizer(colorGrid.length);
		Group gridRoot = myVisualizer.returnVisualGrid(colorGrid);
		return gridRoot;
	}	
}
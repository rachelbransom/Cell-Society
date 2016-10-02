package simulation;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import simulation.types.hierarchy.AbstractSimulation;
import simulation.visuals.SimulationVisualizer;
import simulation.visuals.StateToColorConverter;

//@authour: Rachel Bransom
//@author: Diane Hadley

public class SimulationController {

	private AbstractSimulation mySimulation;
	private SimulationVisualizer myVisualizer;
	private String myShape;
	
	public void initializeSimulation(String filename, String shape){		  
		myShape = shape;
		SimulationFactory factory = new SimulationFactory(filename, myShape);
		mySimulation = factory.makeSimulation();
		
	}
	
	
	
	public Group returnCurrVisualGrid(){
		Color[][] colorGrid = new StateToColorConverter(mySimulation).showCurrColorGrid();
		return makeGridRoot(colorGrid);
	}
	
	public Group returnNextVisualGrid(){
		Color[][] colorGrid = new StateToColorConverter(mySimulation).showNextColorGrid();
		return makeGridRoot(colorGrid);
	}	
	
	private Group makeGridRoot( Color[][] colorGrid ){
		myVisualizer = new SimulationVisualizer(colorGrid.length, myShape);
		Group gridRoot = myVisualizer.returnVisualGrid(colorGrid);
		return gridRoot;
	}
}
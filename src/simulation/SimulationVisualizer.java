package simulation;

import cellUtil.Grid;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

class SimulationVisualizer {

	private Grid myCellGrid;
	private Rectangle[][] myRectGrid;
	
	Node showSimulation(){
		return null;
	}

	void getCurrentGrid(Grid grid){
		myCellGrid = new Grid( grid );
	}
	
	void initRectGrid(){}
	
}

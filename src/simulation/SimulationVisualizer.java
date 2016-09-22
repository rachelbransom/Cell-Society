package simulation;

import cellUtil.Grid;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

class SimulationVisualizer {

	private int mySize;
	private Grid myCellGrid;
	private Rectangle[][] myRectGrid;
	
	Node showSimulation(){
		return null;
	}

	void getCurrentGrid(Grid grid){
		myCellGrid = new Grid( grid );
	}
	
	void initRectGrid(){
		myRectGrid = new Rectangle[mySize][mySize];
		
		for (int i = 0; i < myRectGrid.length; i++) {
			for (int j = 0; j < myRectGrid.length; j++) {
				myRectGrid[i][j] = new Rectangle();
			}
		}
		
	}
	
}

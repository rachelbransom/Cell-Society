package simulation;

import cellUtil.Grid;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

class SimulationVisualizer extends UX {
	private int myGridSize;
	private int myPixelSize;
	private Grid myCellGrid;
	private Rectangle[][] myRectGrid;
	private Group root;
	
	public SimulationVisualizer (int gridPixelSize, Group rootToSet){
		rootToSet = root;
		myPixelSize = gridPixelSize;
	}
	
	Group showSimulation(){
		return root;
	}
	
	void update(){
		
	}

	void setCurrentGrid(Grid grid){
		myCellGrid = new Grid( grid );
		myGridSize = grid.getSize();
		
	}
	
	void initRectGrid(){
		for (int i=0; i<myGridSize; i++){
			for (int j=0; i<myGridSize; j++){
				myRectGrid[i][j] = new Rectangle(myPixelSize/myGridSize,myPixelSize/myGridSize);
				root.getChildren().add(myRectGrid[i][j]);
			}
		}
	}
	
}

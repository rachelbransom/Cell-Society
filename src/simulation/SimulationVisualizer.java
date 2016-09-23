package simulation;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class SimulationVisualizer {

	private Rectangle[][] myRectGrid;
	
	SimulationVisualizer(int size){


		initRectGrid(size);
	}


	Node returnVisualGrid(){
		return null;
	}

	void updateVisuals(Color[][] colorGrid){

		updateGridColor(colorGrid);


	}

	private void updateGridColor(Color[][] colorGrid){

		for (int i = 0; i < myRectGrid.length; i++) {
			for (int j = 0; j < myRectGrid.length; j++) {
				myRectGrid[i][j].setFill( colorGrid[i][j] );
			}
		}

	}

	private void initRectGrid(int size){
		myRectGrid = new Rectangle[size][size];
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				myRectGrid[i][j] = new Rectangle();
			}
		}
		
	}
	
}

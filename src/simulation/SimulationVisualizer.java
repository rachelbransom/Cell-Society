package simulation;




import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

class SimulationVisualizer {
	
	
	private Shape[][] myShapeGrid;
	
	SimulationVisualizer(int size){

		initShapeGrid(size);
	}


	Group returnVisualGrid(){
		Group gridRoot = new Group();
		
		
		return gridRoot;
	}

//	void updateVisuals(Color[][] colorGrid){
//
//		updateGridColor(colorGrid);
//
//
//	}

	private void updateGridColor(Color[][] colorGrid){

		for (int i = 0; i < myShapeGrid.length; i++) {
			for (int j = 0; j < myShapeGrid.length; j++) {
				myShapeGrid[i][j].setFill(colorGrid[i][j]);
			}
		}

	}

	private void initShapeGrid(int size){
		myShapeGrid = new Shape[size][size];
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				myShapeGrid[i][j] = new Rectangle();
			}
		}
		
	}
	
}

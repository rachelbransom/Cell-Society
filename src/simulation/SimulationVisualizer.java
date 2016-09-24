package simulation;


import cellsociety_team23.Main;
import cellsociety_team23.UX;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

class SimulationVisualizer {
	
	
	private Shape[][] myShapeGrid;
	private Group myGridRoot;
	
	SimulationVisualizer(int size){
		initShapeGrid(size);		
	}

	Group returnVisualGrid(Color[][] colorGrid){
		updateGridColor(colorGrid);
		return myGridRoot;
	}
	

	//TODO: update grid vs. initialize grid
	//TODO: clear root between steps
	
//	private void initGridColor(Color[][] colorGrid){
//		
//	}
	
	private void updateGridColor(Color[][] colorGrid){
		myGridRoot = new Group();
		for (int i = 0; i < myShapeGrid.length; i++) {
			for (int j = 0; j < myShapeGrid.length; j++) {
				myShapeGrid[i][j].setFill(colorGrid[i][j]);
				myGridRoot.getChildren().add(myShapeGrid[i][j]);
			}
		}
	}

	private void initShapeGrid(int size){
		myShapeGrid = new Shape[size][size];		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {				
				Rectangle shapeInGrid = new Rectangle();			
				formatRectangle(i, j, shapeInGrid);				
				myShapeGrid[i][j] = shapeInGrid;				
			}
		}		
	}

	private void formatRectangle(int i, int j, Rectangle shapeInGrid) {
		shapeInGrid.setWidth(Main.XSIZE/myShapeGrid[0].length);
		shapeInGrid.setHeight(Main.XSIZE/myShapeGrid.length);
		shapeInGrid.setX(Main.XSIZE/myShapeGrid[0].length*j);
		shapeInGrid.setY(Main.XSIZE/myShapeGrid.length*i + UX.GRID_START);
		
		
	}
	
}

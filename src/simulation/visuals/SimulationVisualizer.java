package simulation.visuals;

/**
 * @author Diane Hadley
 */

import cellsociety_team23.UX;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;

import javafx.scene.shape.Shape;

public class SimulationVisualizer {
	
	
	private Shape[][] myShapeGrid;
	private Group myGridRoot;
	
	public SimulationVisualizer(int size){
		initShapeGrid(size);		
	}

	public Group returnVisualGrid(Color[][] colorGrid){
		updateGridColor(colorGrid);
		return myGridRoot;
	}
	
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
		shapeInGrid.setWidth(UX.GRID_SIZE/myShapeGrid[0].length);
		shapeInGrid.setHeight(UX.GRID_SIZE/myShapeGrid.length);
		shapeInGrid.setX(UX.GRID_START_X+ UX.GRID_SIZE/myShapeGrid[0].length*j);
		shapeInGrid.setY(UX.GRID_START_Y + UX.GRID_SIZE/myShapeGrid.length*i);
		
		
	}
	
	
}

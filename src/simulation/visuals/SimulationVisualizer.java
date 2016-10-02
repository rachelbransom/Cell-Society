package simulation.visuals;

import java.util.HashMap;

import com.sun.javafx.collections.MappingChange.Map;

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
	private HashMap<Color, Integer> populationMap;// = new HashMap<Color, Integer>();
	private Boolean gridLinesVisible = false;
	
	public SimulationVisualizer(int size, Boolean userWantsGridLines) {
		if (userWantsGridLines){
			this.gridLinesVisible = true;
		}
		initShapeGrid(size);
	}

	public Group returnVisualGrid(Color[][] colorGrid) {
		updateGridColor(colorGrid);
		return myGridRoot;
	}

	private void updateGridColor(Color[][] colorGrid) {
		populationMap = new HashMap<Color, Integer>();
		myGridRoot = new Group();
		for (int i = 0; i < myShapeGrid.length; i++) {
			for (int j = 0; j < myShapeGrid.length; j++) {
				myShapeGrid[i][j].setFill(colorGrid[i][j]);
				myGridRoot.getChildren().add(myShapeGrid[i][j]);
				if (populationMap.containsKey(colorGrid[i][j])){
					populationMap.put(colorGrid[i][j], populationMap.get(colorGrid[i][j])+1);
				}
				else {
					populationMap.put(colorGrid[i][j], 1);
				}
			}
		}
	}
	
	public HashMap<Color,Integer> getPopulationMap(){
		return populationMap;
	}
	

	private void initShapeGrid(int size) {
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
		shapeInGrid.setWidth(UX.GRID_SIZE / myShapeGrid[0].length);
		shapeInGrid.setHeight(UX.GRID_SIZE / myShapeGrid.length);
		shapeInGrid.setX(UX.GRID_START_X + UX.GRID_SIZE / myShapeGrid[0].length * j);
		shapeInGrid.setY(UX.GRID_START_Y + UX.GRID_SIZE / myShapeGrid.length * i);
		if (gridLinesVisible){
			shapeInGrid.setStroke(Color.BLACK);
		}
		
	}

}

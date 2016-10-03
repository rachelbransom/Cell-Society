package simulation.visuals;

import java.util.HashMap;

import com.sun.javafx.collections.MappingChange.Map;

/**
 * @author Diane Hadley
 */


import javafx.scene.Group;
import javafx.scene.paint.Color;


import javafx.scene.shape.Shape;
import simulationColorScheme.ColorScheme;

public class SimulationVisualizer {

	
	
	private Shape[][] myShapeGrid; 
	private Group myGridRoot;
	private String myShape;
	private CellShape myCellShape;
	private HashMap<Color, Integer> populationMap;// = new HashMap<Color, Integer>();
	private Boolean gridLinesVisible = false;
	
	public SimulationVisualizer(int size, String shape, Boolean userWantsGridLines){
		if (userWantsGridLines){
			this.gridLinesVisible = true;
		}
		myShape = shape;
		myCellShape = getShape(size);
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
	


	private CellShape getShape(int size){
		switch(myShape) {		
		case("Square"):
			return new Square(size);
		case("Triangle"):
			return new Triangle(size);
		case("Hexagon"):
			return new Hexagon(size); 	    
		}
		return null;
	}
	
	private void initShapeGrid(int size){
		myShapeGrid = new Shape[size][size]; 	
		for (int i = 0; i < size; i++) {
			
			for (int j = 0; j < size; j++) {						
				Shape shapeInGrid = myCellShape.formatShape(i, j, myShapeGrid);				
				if (gridLinesVisible){
					shapeInGrid.setStroke(Color.BLACK);
				}
				myShapeGrid[i][j] = shapeInGrid;				

			}
		}
	}


	
		
		
}

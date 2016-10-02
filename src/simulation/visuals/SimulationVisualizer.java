package simulation.visuals;

/**
 * @author Diane Hadley
 */


import javafx.scene.Group;
import javafx.scene.paint.Color;


import javafx.scene.shape.Shape;

public class SimulationVisualizer {
	
	
	private Shape[][] myShapeGrid; 
	private Group myGridRoot;
	private String myShape;
	private CellShape myCellShape;
	
	public SimulationVisualizer(int size, String shape){
		myShape = shape;
		myCellShape = getShape(size);
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
				myShapeGrid[i][j] = shapeInGrid;				
			}
		}		
	}
	
}

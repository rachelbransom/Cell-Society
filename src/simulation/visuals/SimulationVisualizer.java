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
		initShapeGrid(size);	
		myShape = shape;
		getShape(size);
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

	private void getShape(int size){
		switch(myShape) {
		case("Square"):
			myCellShape = new Square(size);
		case("Triangle"):
			myCellShape = new Triangle(size);
		case("Hexagon"):
			myCellShape = new Hexagon(size); 
		}
	}
	
	private void initShapeGrid(int size){
		myShapeGrid = new Shape[size][size]; 	
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {				
				Shape shapeInGrid;			
				shapeInGrid = myCellShape.formatShape(i, j, myShapeGrid);				
				myShapeGrid[i][j] = shapeInGrid;				
			}
		}		
	}

	
	
	
	
	
}

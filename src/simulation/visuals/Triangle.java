package simulation.visuals;

import cellsociety_team23.ApplicationController;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class Triangle extends CellShape{

	private Polygon triangle;
	
	public Triangle(int size) {
		super(size);
		width = (double)ApplicationController.GRID_SIZE/(double)size * 2 ;
	}
	
	
	@Override
	public Shape formatShape(int i, int j, Shape[][] shapeGrid) {
		triangle = new Polygon();
		
		if (i % 2 == j % 2){
			setPointUpCoordinates((double)i, (double)j);
		}
		
		if (i % 2 != j % 2){
			setPointDownCoordinates((double)i, (double)j);
		}
		return triangle;
		
	}
	
	private void setPointUpCoordinates(double i, double j){
		triangle.getPoints().addAll(new Double[]{
				ApplicationController.GRID_START_X + j/2.0*width, ApplicationController.GRID_START_Y + (1+i)*height,
				ApplicationController.GRID_START_X + (j/2.0+1)*width, ApplicationController.GRID_START_Y + (1+i)*height,
				ApplicationController.GRID_START_X + (j+1)*width/2, ApplicationController.GRID_START_Y + i*height,
		});
	}
	
	private void setPointDownCoordinates(double i, double j){
		triangle.getPoints().addAll(new Double[]{
				ApplicationController.GRID_START_X + j/2*width, ApplicationController.GRID_START_Y + i*height,
				ApplicationController.GRID_START_X + (j/2+1)*width, ApplicationController.GRID_START_Y + i*height,
				ApplicationController.GRID_START_X + (j+1)*width/2, ApplicationController.GRID_START_Y + (i+1)*height,
		});
	}
	
	
}
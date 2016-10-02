package simulation.visuals;

import cellsociety_team23.UX;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class Triangle extends CellShape{

	private Polygon triangle;
	
	public Triangle(int size) {
		super(size);
	}
	
	
	@Override
	public Shape formatShape(int i, int j, Shape[][] shapeGrid) {
		triangle = new Polygon();
		
		if (i % 2 == j % 2){
			setPointUpCoordinates(i, j);
		}
		
		if (i % 2 != j % 2){
			setPointDownCoordinates(i, j);
		}
		return triangle;
		
	}
	
	private void setPointUpCoordinates(int i, int j){
		triangle.getPoints().addAll(new Double[]{
				UX.GRID_START_X + j/2*width, UX.GRID_START_Y + (1+i)*height,
				UX.GRID_START_X + (j/2+1)*width, UX.GRID_START_Y + (1+i)*height,
				UX.GRID_START_X + (j+1)*width/2, UX.GRID_START_Y + i*height,
		});
	}
	
	private void setPointDownCoordinates(int i, int j){
		triangle.getPoints().addAll(new Double[]{
				UX.GRID_START_X + j/2*width, UX.GRID_START_Y + i*height,
				UX.GRID_START_X + (j/2+1)*width, UX.GRID_START_Y + i*height,
				UX.GRID_START_X + (j+1)*width/2, UX.GRID_START_Y + (i+1)*height,
		});
	}
	
	
}
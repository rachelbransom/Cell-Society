package simulation.visuals;

import cellsociety_team23.UX;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Square extends CellShape{
	
	
	public Square(int size) {
		super(size);
	}

	@Override
	public Shape formatShape(int i, int j, Shape[][] shapeGrid) {
		Rectangle rectangle = new Rectangle();
		rectangle.setWidth(width);
		rectangle.setHeight(height);
		rectangle.setX(UX.GRID_START_X + width*j);
		rectangle.setY(UX.GRID_START_Y + height*i);
		return rectangle;
		
	}
	
}
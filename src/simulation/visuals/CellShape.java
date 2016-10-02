package simulation.visuals;

import cellsociety_team23.UX;
import javafx.scene.shape.Shape;

abstract class CellShape{
	
	protected double width;
	protected double height;
	
	
	public CellShape(int size){
		width = UX.GRID_SIZE/size ;
		height = UX.GRID_SIZE/size;		
	}
	
	public abstract Shape formatShape(int i, int j, Shape[][] shapeGrid);
	
}
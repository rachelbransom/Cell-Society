package simulation.visuals;

import cellsociety_team23.ApplicationController;
import javafx.scene.shape.Shape;

public abstract class CellShape{
	
	protected double width;
	protected double height;
	
	
	public CellShape(int size){
		width = (double)(ApplicationController.GRID_SIZE)/(double)(size) ;
		height = (double)ApplicationController.GRID_SIZE/(double)size;		
	}
	
	public abstract Shape formatShape(int i, int j, Shape[][] shapeGrid);
	
}
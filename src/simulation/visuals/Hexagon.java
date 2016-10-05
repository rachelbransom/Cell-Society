package simulation.visuals;

import cellsociety_team23.ApplicationController;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class Hexagon extends CellShape{

	private Polygon hexagon;
	
	
	public Hexagon(int size) {
		super(size);
		width = (double)ApplicationController.GRID_SIZE/(1.5*((double)size-1) + 2) ;
		height = (double)ApplicationController.GRID_SIZE/((double)size + 0.5);		
	}
	
	@Override
	public Shape formatShape(int i, int j, Shape[][] shapeGrid) {
		hexagon = new Polygon();
		double addHeight = 0.0;
		double dI = (double) i;
		double dJ = (double) j;
		
		if (j % 2 == 1){
			addHeight = 0.5;
		}
		hexagon.getPoints().addAll(new Double[]{
				ApplicationController.GRID_START_X + 1.5*dJ*width, ApplicationController.GRID_START_Y + (dI + 0.5 + addHeight)*height,
				ApplicationController.GRID_START_X + (1.5*dJ + 0.5)*width, ApplicationController.GRID_START_Y + (dI + addHeight)*height,
				ApplicationController.GRID_START_X + (1.5*dJ + 1.5)*width, ApplicationController.GRID_START_Y + (dI + addHeight)*height,
				ApplicationController.GRID_START_X + (1.5*dJ + 2)*width, ApplicationController.GRID_START_Y + (dI+0.5 + addHeight)*height,				
				ApplicationController.GRID_START_X + (1.5*dJ + 1.5)*width, ApplicationController.GRID_START_Y + (dI+1 + addHeight)*height,
				ApplicationController.GRID_START_X + (1.5*dJ + 0.5)*width, ApplicationController.GRID_START_Y + (dI+1 + addHeight)*height
		});
	
		
		return hexagon;
		
	}
}
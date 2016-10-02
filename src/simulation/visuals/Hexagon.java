package simulation.visuals;

import cellsociety_team23.UX;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class Hexagon extends CellShape{

	private Polygon hexagon;
	
	
	public Hexagon(int size) {
		super(size);
		width = UX.GRID_SIZE/(1.5*(size-1) + 2) ;
		height = UX.GRID_SIZE/(size + 0.5);		
	}
	
	@Override
	public Shape formatShape(int i, int j, Shape[][] shapeGrid) {

		hexagon = new Polygon();
		double addHeight = 0;
		
		if (j % 2 == 1){
			addHeight = 0.5;
		}
		
		hexagon.getPoints().addAll(new Double[]{
				UX.GRID_START_X + 3/2*j*width, UX.GRID_START_Y + (i+0.5 + addHeight)*height,
				UX.GRID_START_X + (3/2*j + 0.5)*width, UX.GRID_START_Y + (i + addHeight)*height,
				UX.GRID_START_X + (3/2*j + 1.5)*width, UX.GRID_START_Y + (i + addHeight)*height,
				UX.GRID_START_X + (3/2*j + 2)*width, UX.GRID_START_Y + (i+0.5 + addHeight)*height,
				UX.GRID_START_X + (3/2*j + 0.5)*width, UX.GRID_START_Y + (i+1 + addHeight)*height,
				UX.GRID_START_X + (3/2*j + 1.5)*width, UX.GRID_START_Y + (i+1 + addHeight)*height
		});
		
		return hexagon;
		
	}
}
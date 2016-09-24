package simulation.types;

import java.awt.Point;
import java.util.HashMap;

import cellUtil.Cell;
import cellUtil.Grid;
import cellUtil.CellState.Segregation;
import javafx.scene.paint.Color;

public class SegregationSimulation extends AbstractSimulation {

	public SegregationSimulation(Grid inputGrid, float satisfaction) {
		super(inputGrid);
		// TODO Auto-generated constructor stub
	}

	/*----------------- Overriden Methods -----------------------------*/
	
	@Override
	public Color[][] showColorGrid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void updateCell(Cell curr) {
		
		Segregation currState = (Segregation) curr.getActor().getState();
		
		Point location = curr.getLocation();
		Cell newCell = myNextGrid.getCell(location.x, location.y);
		
		if (currState == Segregation.EMPTY) {
			//
		}
			

	}

	@Override
	protected void initColorMap() {
		myColorMap = new HashMap<Enum, Color>();
		
		myColorMap.put(Segregation.EMPTY, Color.WHITE);
		myColorMap.put(Segregation.POP_ONE, Color.BLUE);
		myColorMap.put(Segregation.POP_TWO, Color.RED);

	}

}

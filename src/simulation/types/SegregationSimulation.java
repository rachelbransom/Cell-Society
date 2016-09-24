package simulation.types;

import java.util.HashMap;
import java.util.Stack;

import cellUtil.Actor;
import cellUtil.Cell;
import cellUtil.CellState.Segregation;
import cellUtil.Grid;
import javafx.scene.paint.Color;

public class SegregationSimulation extends AbstractSimulation {
	private double mySatisfactionThreshold; // Minimum ratio of population being comfortable with the current location
	private Stack<Actor> myUnsatisfiedCitizens; // Holds the unsatisfied Actors from one pass of the grid
	
	
	private static final Enum AMP = Segregation.POP_ONE; // Short Nickname for population one
	private static final Enum OHM = Segregation.POP_TWO; // Short Nickname for population two
	private static final Enum EMPTY = Segregation.EMPTY;
	
	public SegregationSimulation(Grid inputGrid, double satisfaction) {
		super(inputGrid);
		myCurrGrid.setNeighbors(SimulationType.SEGREGATION);
	}

	/*----------------- Overriden Methods -----------------------------*/
	
	@Override
	protected void updateGrid(){
		
		for (int i = 0; i < this.mySize; i++) {
			for (int j = 0; j < mySize; j++) {
				updateCell( myCurrGrid.getCell(i, j));
			}
		}

		
		
	}
	
	@Override
	protected void updateCell(Cell curr) {

		
		
	}

	@Override
	protected void initColorMap() {
		this.myColorMap = new HashMap<Enum, Color>();
		
		myColorMap.put(EMPTY, Color.WHITE);
		myColorMap.put(AMP, Color.RED);
		myColorMap.put(OHM, Color.BLUE);
		
	}

	/*----------------- Private / Helper Methods -----------------------------*/

	
	
}

package simulation.types;

import java.awt.Point;
import java.util.HashMap;

import java.util.HashMap;
import java.util.Stack;

import cellUtil.Actor;
import cellUtil.Cell;
import cellUtil.CellState.Segregation;
import cellUtil.Grid;
import cellUtil.CellState.Segregation;
import javafx.scene.paint.Color;

public class SegregationSimulation extends AbstractSimulation {
	private double mySatisfactionThreshold; // Minimum ratio of population being comfortable with the current location
	private Stack<Point> myEmptyCellPoints; //Holds empty cells 
	private Stack<Cell> myUnsatisfiedCitizens; // Holds the unsatisfied Actors from one pass of the grid
		
	
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
		relocateUnsatisfiedCitizens();
	}
	
	@Override
	protected void updateCell(Cell curr) {
		
		Segregation currState = (Segregation) curr.getActor().getState();
		
		Point location = curr.getLocation();
		Cell newCell = myNextGrid.getCell(location.x, location.y);
		
		if (currState == Segregation.EMPTY) {
			myEmptyCellPoints.push(curr.getLocation());
		}
		else if (currState == Segregation.POP_ONE){
			if (percentNeighborsSame(Segregation.POP_ONE, curr) < mySatisfactionThreshold) {
				myUnsatisfiedCitizens.push(curr);
			}
			else {
				myNextGrid.setCell(location.x, location.y, newCell);
			}
		}
		else if (currState == Segregation.POP_TWO){
			if (percentNeighborsSame(Segregation.POP_TWO, curr) < mySatisfactionThreshold) {
				myUnsatisfiedCitizens.push(curr);
			}
			else {
				myNextGrid.setCell(location.x, location.y, newCell);
			}
		}
	}
	
	private float percentNeighborsSame(Enum state, Cell cell){
		return numberNeighborsWithState(state, cell)/
				(numberNeighborsWithState(Segregation.POP_ONE, cell) +
						(numberNeighborsWithState(Segregation.POP_TWO, cell)));
	}
	
	private void relocateUnsatisfiedCitizens(){
		while (!myUnsatisfiedCitizens.isEmpty()){
			Cell cellToRelocate = myUnsatisfiedCitizens.pop();
			Point point = myEmptyCellPoints.pop();
			Cell newCell = new Cell(cellToRelocate);
			newCell.setActor(cellToRelocate.getActor());
			myNextGrid.setCell(point.x, point.y, newCell);
		}
		while (!myEmptyCellPoints.isEmpty()){
			Point emptyPoint = myEmptyCellPoints.pop();
			Cell newCell = new Cell();
			newCell.setActor(new Actor(Segregation.EMPTY));
			myNextGrid.setCell(emptyPoint.x, emptyPoint.y, newCell);
		}
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

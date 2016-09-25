package simulation.types;

import java.awt.Point;
import java.util.Collections;
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
	private double mySatisfactionThreshold = 0.9; // Minimum ratio of population being comfortable with the current location
	private Stack<Point> myEmptyCellPoints; //Holds empty cells 
	private Stack<Actor> myUnsatisfiedCitizens; // Holds the unsatisfied Actors from one pass of the grid
	boolean initialCallToUpdateGrid = true;	
	
	private static final Enum AMP = Segregation.POP_ONE; // Short Nickname for population one
	private static final Enum OHM = Segregation.POP_TWO; // Short Nickname for population two
	private static final Enum EMPTY = Segregation.EMPTY;
	
	public SegregationSimulation(Grid inputGrid, double satisfaction) {
		super(inputGrid);
		myCurrGrid.setNeighbors(SimulationType.SEGREGATION);
		myEmptyCellPoints = new Stack<Point>();
		myUnsatisfiedCitizens = new Stack<Actor>();
	}

	/*----------------- Overriden Methods -----------------------------*/
	
	
	
	
	@Override
	protected void updateGrid(){	
		//System.out.println(myUnsatisfiedCitizens);
		//System.out.println(myEmptyCellPoints);
		myNextGrid  = new Grid(myCurrGrid.getSize());
		
		for (int i = 0; i < this.mySize; i++) {
			for (int j = 0; j < mySize; j++) {
				updateCell( myCurrGrid.getCell(i, j));
			}
		}
		
		//if (!initialCallToUpdateGrid){
		relocateUnsatisfiedCitizens();
		myCurrGrid = myNextGrid;
		myCurrGrid.setNeighbors(SimulationType.SEGREGATION);
		//}
		//initialCallToUpdateGrid = false;
//		for (int i = 0; i < this.mySize; i++) {
//			for (int j = 0; j < mySize; j++) {
//				System.out.println(myCurrGrid.getCell(i, j).getActor().getState());
//			}
//		}
	}
	
	@Override
	protected void updateCell(Cell curr) {
		
		Segregation currState = (Segregation) curr.getActor().getState();
		
		Point location = curr.getLocation();
		Cell newCell = new Cell(curr);
		
		if (currState.equals(EMPTY)) {
			myEmptyCellPoints.push(curr.getLocation());
		}
		else if (currState.equals(OHM)){
			//System.out.println("neighbors percent: " + percentNeighborsSame(Segregation.POP_ONE, curr));
			//System.out.println(mySatisfactionThreshold);
			if (percentNeighborsSame(Segregation.POP_ONE, curr) < mySatisfactionThreshold) {
				myUnsatisfiedCitizens.push(curr.getActor());
				myEmptyCellPoints.push(curr.getLocation());
				curr.setActor( new Actor(EMPTY) );
			}
			else {
				myNextGrid.setCell(location.x, location.y, newCell);
			}
		}
		else if (currState.equals(AMP)){
			if (percentNeighborsSame(Segregation.POP_TWO, curr) < mySatisfactionThreshold) {
				myUnsatisfiedCitizens.push(curr.getActor());
				myEmptyCellPoints.push(curr.getLocation());
				curr.setActor(new Actor(EMPTY));
			}
			else {
				myNextGrid.setCell(location.x, location.y, newCell);
			}
		}
	}
	
	private float percentNeighborsSame(Enum state, Cell cell){
		float neighbors = (numberNeighborsWithState(Segregation.POP_ONE, cell) +
				(numberNeighborsWithState(Segregation.POP_TWO, cell)));
		if (neighbors!= 0)
			return numberNeighborsWithState(state, cell)/neighbors;	
		else
			return 0;			
	}
	
	private void relocateUnsatisfiedCitizens(){
		Collections.shuffle(myEmptyCellPoints);
		Collections.shuffle(myUnsatisfiedCitizens);
		while (!myUnsatisfiedCitizens.isEmpty()){
			//Cell cellToRelocate = myUnsatisfiedCitizens.pop();
			Point point = myEmptyCellPoints.pop();
			myNextGrid.getCell(point.x, point.y).setActor(myUnsatisfiedCitizens.pop());
		}
		while (!myEmptyCellPoints.isEmpty()){
			Point emptyPoint = myEmptyCellPoints.pop();
			myNextGrid.getCell(emptyPoint.x, emptyPoint.y).setActor(new Actor(EMPTY));;
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
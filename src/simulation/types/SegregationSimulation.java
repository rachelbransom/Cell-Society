package simulation.types;

import java.awt.Point;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;
import cellUtil.Actor;
import cellUtil.Cell;
import cellUtil.CellState.Segregation;
import cellUtil.Grid;
import javafx.scene.paint.Color;


public class SegregationSimulation extends AbstractSimulation {
<<<<<<< HEAD
	private double mySatisfactionThreshold;
	private Stack<Point> myEmptyCellPoints;
=======
	private double mySatisfactionThreshold; // Minimum ratio of population being comfortable with the current location
	private Stack<Point> myEmptyCellPoints; //Holds empty cells 
>>>>>>> ee245e5ebf04edce8a2eea42db232ee40abe55ac
	private Stack<Actor> myUnsatisfiedCitizens; // Holds the unsatisfied Actors from one pass of the grid
	boolean initialCallToUpdateGrid = true;	
	
	private static final Enum AMP = Segregation.POP_ONE; // Short Nickname for population one
	private static final Enum OHM = Segregation.POP_TWO; // Short Nickname for population two
	private static final Enum EMPTY = Segregation.EMPTY;
	
	public SegregationSimulation(Grid inputGrid, double satisfaction) {
		super(inputGrid);
		mySatisfactionThreshold = satisfaction;
		myCurrGrid.setNeighbors(SimulationType.SEGREGATION);
		mySatisfactionThreshold = 0.5;
		myEmptyCellPoints = new Stack<Point>();
		myUnsatisfiedCitizens = new Stack<Actor>();
	}

<<<<<<< HEAD
	/*----------------- Overriden Methods -----------------------------*/	
	
	@Override
	protected void updateGrid(){	
=======
	/*----------------- Overriden Methods -----------------------------*/
		
	@Override
	protected void updateGrid(){	
		
>>>>>>> ee245e5ebf04edce8a2eea42db232ee40abe55ac
		myNextGrid  = new Grid(myCurrGrid.getSize());
		
		for (int i = 0; i < this.mySize; i++) {
			for (int j = 0; j < mySize; j++) {
				updateCell( myCurrGrid.getCell(i, j));
			}
		}
		
		relocateUnsatisfiedCitizens();
		myCurrGrid = myNextGrid;
		myCurrGrid.setNeighbors(SimulationType.SEGREGATION);
<<<<<<< HEAD
=======
		
>>>>>>> ee245e5ebf04edce8a2eea42db232ee40abe55ac
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
<<<<<<< HEAD
			if (percentNeighborsSame(Segregation.POP_ONE, curr) < mySatisfactionThreshold) {
=======
			if ( percentNeighborsSame(OHM, curr) <= mySatisfactionThreshold) {
>>>>>>> ee245e5ebf04edce8a2eea42db232ee40abe55ac
				myUnsatisfiedCitizens.push(curr.getActor());
				myEmptyCellPoints.push(curr.getLocation());
			}
			else {
				myNextGrid.setCell(location.x, location.y, newCell);
			}
		}
		else if (currState.equals(AMP)){
			if (percentNeighborsSame(AMP, curr) <= mySatisfactionThreshold) {
				myUnsatisfiedCitizens.push(curr.getActor());
				myEmptyCellPoints.push(curr.getLocation());
<<<<<<< HEAD
				curr.setActor( new Actor(EMPTY) );
=======
>>>>>>> ee245e5ebf04edce8a2eea42db232ee40abe55ac
			}
			else {
				myNextGrid.setCell(location.x, location.y, newCell);
			}
		}
	}
	
<<<<<<< HEAD
=======
	private double percentNeighborsSame(Enum state, Cell cell){
		
		double neighbors = (double) cell.numberNeighborsWithState(AMP) + cell.numberNeighborsWithState(OHM);
		
		if(neighbors != 0) 
			return (double) cell.numberNeighborsWithState(state) / neighbors;
		else
			return 0;
	}
	
>>>>>>> ee245e5ebf04edce8a2eea42db232ee40abe55ac
	private void relocateUnsatisfiedCitizens(){
		Collections.shuffle(myEmptyCellPoints);
		Collections.shuffle(myUnsatisfiedCitizens);
		
		while (!myUnsatisfiedCitizens.isEmpty()){
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

	
	
}
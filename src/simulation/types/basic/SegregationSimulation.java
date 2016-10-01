package simulation.types.basic;

import java.awt.Point;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;
import cellUtil.Actor;
import cellUtil.Cell;
import cellUtil.CellState.Segregation;
import cellUtil.Grid;
import javafx.scene.paint.Color;
import simulation.types.AbstractSimulation;
import simulation.types.SimulationType;


public class SegregationSimulation extends AbstractSimulation {
	private double mySatisfactionThreshold; // Minimum ratio of population being comfortable with the current location
	private Stack<Point> myEmptyCellPoints; //Holds empty cells 
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

	/*----------------- Overriden Methods -----------------------------*/
		
	@Override
	protected void updateGrid(){	
		
		myNextGrid  = new Grid(myCurrGrid.getSize());
		
		for (int i = 0; i < this.mySize; i++) {
			for (int j = 0; j < mySize; j++) {
				updateCell( myCurrGrid.getCell(i, j));
			}
		}
		
		relocateUnsatisfiedCitizens();
		myCurrGrid = myNextGrid;
		myCurrGrid.setNeighbors(SimulationType.SEGREGATION);
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

			if ( percentNeighborsSame(OHM, curr) <= mySatisfactionThreshold) {
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
			}
			else {
				myNextGrid.setCell(location.x, location.y, newCell);
			}
		}
	}
	
	private double percentNeighborsSame(Enum state, Cell cell){
		
		double neighbors = (double) cell.numberNeighborsWithState(AMP) + cell.numberNeighborsWithState(OHM);
		
		if(neighbors != 0) 
			return (double) cell.numberNeighborsWithState(state) / neighbors;
		else
			return 0;
	}
	
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
	
	protected void initPopulationMap(){
		myPopulationMap = new HashMap<Color, Integer>();
		
		myPopulationMap.put(Color.WHITE, 0);
		myPopulationMap.put(Color.RED, 0);
		myPopulationMap.put(Color.BLUE, 0);
	}

	
	
}
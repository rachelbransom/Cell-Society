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
	private double mySatisfactionThreshold;
	private Stack<Point> myEmptyCellPoints;
	private Stack<Actor> myUnsatisfiedCitizens; // Holds the unsatisfied Actors from one pass of the grid
	boolean initialCallToUpdateGrid = true;	
	
	private static final Enum AMP = Segregation.POP_ONE; // Short Nickname for population one
	private static final Enum OHM = Segregation.POP_TWO; // Short Nickname for population two
	private static final Enum EMPTY = Segregation.EMPTY;
	
	public SegregationSimulation(Grid inputGrid, double satisfaction) {
		super(inputGrid);
		mySatisfactionThreshold = satisfaction;
		myCurrGrid.setNeighbors(SimulationType.SEGREGATION);
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
				curr.setActor( new Actor(EMPTY) );
			}
			else {
				myNextGrid.setCell(location.x, location.y, newCell);
			}
		}
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

	
	
}
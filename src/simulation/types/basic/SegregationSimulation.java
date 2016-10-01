package simulation.types.basic;

import java.awt.Point;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;
<<<<<<< HEAD
import cellUtil.Actor;
import cellUtil.Cell;
import cellUtil.CellState.Segregation;
import cellUtil.Grid;
import javafx.scene.chart.XYChart;
=======

import cell.Actor;
import cell.Cell;
import cell.CellState.Segregation;
import grid.Grid;
>>>>>>> 8c7f8b03adcacf4ed37493f9bbd99b49dd48af08
import javafx.scene.paint.Color;
import simulation.types.SimulationType;
import simulation.types.hierarchy.AbstractSimulation;

public class SegregationSimulation extends AbstractSimulation {
	private double mySatisfactionThreshold; // Minimum ratio of population being
											// comfortable with the current
											// location
	private Stack<Point> myEmptyCellPoints; // Holds empty cells
	private Stack<Actor> myUnsatisfiedCitizens; // Holds the unsatisfied Actors
												// from one pass of the grid
	private int populationOne, populationTwo, counter;
	boolean initialCallToUpdateGrid = true;

	private static final Enum AMP = Segregation.POP_ONE; // Short Nickname for
															// population one
	private static final Enum OHM = Segregation.POP_TWO; // Short Nickname for
															// population two
	private static final Enum EMPTY = Segregation.EMPTY;

	public SegregationSimulation(Grid inputGrid, double satisfaction) {
		super(inputGrid);
		mySatisfactionThreshold = satisfaction;
		getCurrGrid().setNeighbors(SimulationType.SEGREGATION);
		mySatisfactionThreshold = 0.5;
		myEmptyCellPoints = new Stack<Point>();
		myUnsatisfiedCitizens = new Stack<Actor>();
		initPopulationGraph();
	}

	/*----------------- Overriden Methods -----------------------------*/

	@Override

	protected void updateGrid(){	
		super.updateGrid();
		relocateUnsatisfiedCitizens();
		getCurrGrid().setNeighbors(SimulationType.SEGREGATION);
		}
	
	@Override
	protected void updateCell(Cell curr) {

		Segregation currState = (Segregation) curr.getActor().getState();

		Point location = curr.getLocation();
		Cell newCell = new Cell(curr);

		if (currState.equals(EMPTY)) {
			myEmptyCellPoints.push(curr.getLocation());

		}
		else{ // Population Neighbor Logic
			if ( percentNeighborsSame(curr.getActor().getState(), curr) <= mySatisfactionThreshold) {
				myUnsatisfiedCitizens.push(curr.getActor());
				myEmptyCellPoints.push(curr.getLocation());
			}
			else {
				getNextGrid().setCell(location.x, location.y, newCell);
			}
		}

	}


	private double percentNeighborsSame(Enum state, Cell cell){
		
		double neighbors = (double) cell.numberNeighborsWithState(AMP) + cell.numberNeighborsWithState(OHM);

		if (neighbors != 0)
			return (double) cell.numberNeighborsWithState(state) / neighbors;
		else
			return 0;
	}

	private void relocateUnsatisfiedCitizens() {
		Collections.shuffle(myEmptyCellPoints);
		Collections.shuffle(myUnsatisfiedCitizens);

		while (!myUnsatisfiedCitizens.isEmpty()) {
			Point point = myEmptyCellPoints.pop();

			getCurrGrid().getCell(point.x, point.y).setActor(myUnsatisfiedCitizens.pop());
		}
		while (!myEmptyCellPoints.isEmpty()) {
			Point emptyPoint = myEmptyCellPoints.pop();
			getCurrGrid().getCell(emptyPoint.x, emptyPoint.y).setActor(new Actor(EMPTY));;
		}
	}

	@Override
	protected void initColorMap() {
		this.myColorMap = new HashMap<Enum, Color>();

		myColorMap.put(EMPTY, Color.WHITE);
		myColorMap.put(AMP, Color.RED);
		myColorMap.put(OHM, Color.BLUE);
	}

	protected void initPopulationMap() {
		myPopulationMap = new HashMap<Color, Integer>();

		myPopulationMap.put(Color.WHITE, 0);
		myPopulationMap.put(Color.RED, 0);
		myPopulationMap.put(Color.BLUE, 0);
	}

	protected void initPopulationGraph() {
		populationOne = 0;
		populationTwo = 0;
		super.initPopulationGraphSuper();

		XYChart.Series series1 = new XYChart.Series<>();
		XYChart.Series series2 = new XYChart.Series<>();
		lineChart.getData().addAll(series1, series2);
	}

	protected void updateChart() {
		XYChart.Series series1 = new XYChart.Series<>();
		XYChart.Series series2 = new XYChart.Series<>();
		lineChart.getData().get(0).getData().addAll(new XYChart.Data(counter, populationOne),
				new XYChart.Data(counter, populationTwo));

	}

}
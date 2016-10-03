package simulation.types.basic;

import java.awt.Point;
import java.util.Random;

import javafx.scene.chart.XYChart;
import cell.Actor;
import grid.BorderType;
import cell.Cell;
import cell.CellState.SpreadingFire;
import simulation.types.SimulationType;


import simulation.types.hierarchy.AbstractSimulation;
import grid.Grid;


public class SpreadingFireSimulation extends AbstractSimulation {
	private int tree, burning, counter;
	private double myProbCatch;
	private Random myRandom;

	public SpreadingFireSimulation( Grid inputGrid , double probCatch){

		super( inputGrid );

		//initPopulationCounts(inputGrid);

		getCurrGrid().setNeighbors(SimulationType.SPREADING_FIRE, BorderType.TOROID);
		myProbCatch = probCatch;
		myRandom = new Random();
		//initPopulationGraph();
	}

	/*----------------- Overridden Methods -----------------------------*/

	@Override
	public void updateGrid(){
		super.updateGrid();
		counter++;
		//this.updateChart();
		getCurrGrid().setNeighbors(SimulationType.SPREADING_FIRE, BorderType.TOROID);
	}

	@Override
	protected void updateCell(Cell currCell) {

		// Set intermediate Variables
		SpreadingFire currState = (SpreadingFire) currCell.getActor().getState();

		Point location = currCell.getLocation();
		Cell newCell = new Cell(currCell);

		// If cell is empty or on fire, next turn it's 
		if( currState == SpreadingFire.EMPTY || currState == SpreadingFire.BURNING ){
			if (currState.equals(SpreadingFire.BURNING)){
				burning--;
			}
			newCell.setActor( new Actor(SpreadingFire.EMPTY) );
		}

		// If cell has a tree then check for chance of burning from neighbors
		if( currState == SpreadingFire.TREE )
			for (Cell neighbor : currCell.getNeighbors())
				if ( neighbor.getActor().getState().equals(SpreadingFire.BURNING) && myRandom.nextDouble() < myProbCatch ) {
					burning++;
					newCell.setActor( new Actor(SpreadingFire.BURNING) );
				}

		getNextGrid().setCell(location.x, location.y, newCell);
	}

}


package simulation.types.basic;

import java.awt.Point;
import java.util.Random;

import cell.Actor;
import cell.BorderType;
import cell.Cell;
import cell.CellState.SpreadingFire;
import javafx.scene.paint.Color;
import simulation.types.SimulationType;
import simulation.types.hierarchy.AbstractSimulation;
import grid.Grid;

public class SpreadingFireSimulation extends AbstractSimulation {

	private double myProbCatch;
	private Random myRandom;

	public SpreadingFireSimulation( Grid inputGrid , double probCatch){
		super( inputGrid );
		getCurrGrid().setNeighbors(SimulationType.SPREADING_FIRE, BorderType.TOROID);
		myProbCatch = probCatch;
		myRandom = new Random();
	}

	/*----------------- Overridden Methods -----------------------------*/

	@Override
	public void updateGrid(){
		super.updateGrid();
		//getCurrGrid().setNeighbors(SimulationType.SPREADING_FIRE, BorderType.TOROID);
	}

	@Override
	protected void updateCell(Cell currCell) {

		// Set intermediate Variables
		SpreadingFire currState = (SpreadingFire) currCell.getActor().getState();

		Point location = currCell.getLocation();
		Cell newCell = new Cell(currCell);

		// If cell is empty or on fire, next turn it's 
		if( currState == SpreadingFire.EMPTY || currState == SpreadingFire.BURNING ){
			newCell.setActor( new Actor(SpreadingFire.EMPTY) );
		}

		// If cell has a tree then check for chance of burning from neighbors
		if( currState == SpreadingFire.TREE )
			for (Cell neighbor : currCell.getNeighbors())
				if ( neighbor.getActor().getState().equals(SpreadingFire.BURNING) && myRandom.nextDouble() < myProbCatch ) {
					// System.out.println(newCell.getLocation().toString() + "has burned");
					newCell.setActor( new Actor(SpreadingFire.BURNING) );
				}

		getNextGrid().setCell(location.x, location.y, newCell);
	}

}


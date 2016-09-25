package simulation.types;

import java.awt.Point;
import java.util.HashMap;
import java.util.Random;

import javafx.scene.paint.Color;
import cellUtil.Actor;
import cellUtil.Cell;
import cellUtil.Grid;
import cellUtil.CellState.GameOfLife;
import cellUtil.CellState.SpreadingFire;

public class SpreadingFireSimulation extends AbstractSimulation {

	private double myProbCatch;
	private Random myRandom;

	public SpreadingFireSimulation( Grid inputGrid , double probCatch){
		super( inputGrid );
		myCurrGrid.setNeighbors(SimulationType.SPREADING_FIRE);
		myProbCatch = probCatch;
		myRandom = new Random();
	}

	/*----------------- Overridden Methods -----------------------------*/

	@Override
	public void updateGrid(){
		super.updateGrid();
		myCurrGrid.setNeighbors(SimulationType.SPREADING_FIRE);
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

		myNextGrid.setCell(location.x, location.y, newCell);

	}

	@Override
	protected void initColorMap() {
		myColorMap = new HashMap<Enum, Color>();		

		myColorMap.put(SpreadingFire.EMPTY, Color.YELLOW);
		myColorMap.put(SpreadingFire.TREE, Color.GREEN);
		myColorMap.put(SpreadingFire.BURNING, Color.RED);

	}
	
}


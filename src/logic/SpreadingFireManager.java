package logic;

import java.awt.Point;
import java.util.Random;

import logic.CellState.SpreadingFire;

class SpreadingFireManager extends GridManager {

	private double myProbCatch;
	private Random myRandom;

	SpreadingFireManager(){
		myProbCatch = 0.5;
		myRandom = new Random(1234);
	}

	protected void updateCell(Cell currCell) {
		
		// Set intermediate Variables
		SpreadingFire currState = (SpreadingFire) currCell.getActor().getState();
		
		Point location = currCell.getLocation();
		int x = location.x;
		int y = location.y;

		Cell newCell = myNewGrid.getCell(x, y);
		
		// If cell is empty or on fire, next turn it's 
		if( currState == SpreadingFire.EMPTY || currState == SpreadingFire.BURNING ){
			newCell.getActor().changeState(SpreadingFire.EMPTY);
			return;
		}

		// If cell has a tree then check for chance of burning from neighbors
		if( currState == SpreadingFire.TREE )
			for (Cell neighbor : currCell.getNeighbors())
				if ( neighbor.getActor().getState().equals(SpreadingFire.BURNING) && myRandom.nextDouble() < myProbCatch ) 
					newCell.getActor().changeState(SpreadingFire.EMPTY);

	}

}

package simulation;

import java.awt.Point;
import java.util.HashMap;
import java.util.Random;

import javafx.scene.paint.Color;

import cellUtil.Cell;
import cellUtil.Grid;
import cellUtil.CellState.SpreadingFire;

class SpreadingFireSimulation extends AbstractSimulation {

	private float myProbCatch;
	private Random myRandom;
	
	SpreadingFireSimulation( Grid inputGrid , float probCatch){
		super( inputGrid );
		
		myProbCatch = probCatch;
		myRandom = new Random(1234);
	}

	@Override
	protected void updateCell(Cell currCell) {
		
		// Set intermediate Variables
		SpreadingFire currState = (SpreadingFire) currCell.getActor().getState();
		
		Point location = currCell.getLocation();
		Cell newCell = myNextGrid.getCell(location.x, location.y);
		
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

	@Override
	protected void initColorMap() {
		myColorMap = new HashMap<Enum, Color>();		
		
		myColorMap.put(SpreadingFire.EMPTY, Color.YELLOW);
		myColorMap.put(SpreadingFire.TREE, Color.GREEN);
		myColorMap.put(SpreadingFire.BURNING, Color.RED);
		
	}

	
	
}

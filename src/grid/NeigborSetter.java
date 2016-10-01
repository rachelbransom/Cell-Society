package grid;

import cell.Cell;
import simulation.types.SimulationType;

public class NeigborSetter {

	GenericGrid<Cell> myCellGrid;

	public NeigborSetter( GenericGrid<Cell> cellGrid ) {
		myCellGrid = cellGrid;
	}

	public void setNeighbors(SimulationType simType){

		for (Cell c: myCellGrid) {

			int x = c.getLocation().x;
			int y = c.getLocation().y;
			
			c.getNeighbors().clear();
			
			if( shouldSetSide(simType) ) setSideNeighbors(x, y, c);

			if(  shouldSetAll(simType) ) setFullSquareNeighbors(x, y, c);

		}

	}

	private boolean shouldSetSide(SimulationType simType){
		return simType.equals(SimulationType.GAME_OF_LIFE) || 
				simType.equals(SimulationType.SEGREGATION);
	}
	
	private boolean shouldSetAll(SimulationType simType){
		return simType.equals(SimulationType.WA_TOR_WORLD) ||
				simType.equals(SimulationType.SPREADING_FIRE);
	}
	
	private void setSideNeighbors(int x, int y, Cell currCell){
		// Top Middle
		if(myCellGrid.inBounds(x     , y - 1)) currCell.connectTo(myCellGrid.get(x    , y - 1 ));
		// Right Side
		if(myCellGrid.inBounds(x + 1 , y    )) currCell.connectTo(myCellGrid.get(x + 1, y     ));
		// Bottom Middle
		if(myCellGrid.inBounds(x     , y + 1)) currCell.connectTo(myCellGrid.get(x    , y + 1 ));
		// Left Side
		if(myCellGrid.inBounds(x - 1 , y    )) currCell.connectTo(myCellGrid.get(x - 1, y     ));

	}

	private void setCornerNeighbors(int i, int j, Cell currCell){
		// Top Left
		if(myCellGrid.inBounds(i - 1 , j - 1)) currCell.connectTo(myCellGrid.get(i - 1, j - 1));
		// Top Right
		if(myCellGrid.inBounds(i + 1 , j - 1)) currCell.connectTo(myCellGrid.get(i + 1, j - 1));
		// Bottom Right
		if(myCellGrid.inBounds(i + 1 , j + 1)) currCell.connectTo(myCellGrid.get(i + 1, j + 1));
		// Bottom Left
		if(myCellGrid.inBounds(i - 1 , j + 1)) currCell.connectTo(myCellGrid.get(i - 1, j + 1));
	}
	
	private void setFullSquareNeighbors(int i, int j, Cell currCell){
		setSideNeighbors(i, j, currCell);
		setCornerNeighbors(i, j, currCell);
	}

}

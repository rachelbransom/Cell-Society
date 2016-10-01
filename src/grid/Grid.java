package grid;

import cell.BorderType;
import cell.Cell;
import simulation.types.SimulationType;

public class Grid {

	private Cell[][] myCellGrid;
	private int mySize;

	public Grid(int n){
		
		mySize = n;
		myCellGrid = new Cell[mySize][mySize];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				myCellGrid[i][j] = new Cell(i,j);
			}		}
	}
	
	public Grid( Grid that ){
		
		this.mySize = that.mySize;
		myCellGrid = new Cell[mySize][mySize];
		
		for (int i = 0; i < that.mySize; i++) {
			for (int j = 0; j < that.mySize; j++) {
				myCellGrid[i][j] = new Cell ( that.myCellGrid[i][j] );
			}
		}
	}

	public int getSize(){
		return mySize;
	}
	
	public Cell getCell(int x, int y){
		return myCellGrid[x][y];
	}
		
	public void setCell(int x, int y, Cell input){
		myCellGrid[x][y] = new Cell(input);
	}
	
	public boolean inBounds(int x, int y){
		boolean inX = 0 <= x && x < mySize;
		boolean inY = 0 <= y && y < mySize;
				
		return inX && inY;
	}

	/**
	 * Sets edge neighbors as connecting, thus forming a toroid
	 */
	private void setToroid(){
		
		// Connect Top and Bottom rows
		for (int x = 0; x < mySize; x++) {
			getCell(x, 0).connectTo(getCell(x, mySize - 1));
		}
		
		// Connect Right and Left sides
		for (int y = 0; y < myCellGrid.length; y++) {
			getCell(0, y).connectTo(getCell(mySize - 1, y));
		}
	}
	
	public void setFullSquareNeighbors(int i, int j, Cell currCell){
		
		setSideNeighbors(i, j, currCell);
		
		// Top Left
		if(inBounds(i - 1 , j - 1)) currCell.connectTo(getCell(i - 1, j - 1));
		// Top Right
		if(inBounds(i + 1 , j - 1)) currCell.connectTo(getCell(i + 1, j - 1));
		// Bottom Right
		if(inBounds(i + 1 , j + 1)) currCell.connectTo(getCell(i + 1, j + 1));
		// Bottom Left
		if(inBounds(i - 1 , j + 1)) currCell.connectTo(getCell(i - 1, j + 1));
		
	}
	
	public void setSideNeighbors(int i, int j, Cell currCell){
		currCell.getNeighbors().clear();

		// Top Middle
		if(inBounds(i     , j - 1)) currCell.connectTo(getCell(i    , j - 1 ));
		// Right Side
		if(inBounds(i + 1 , j    )) currCell.connectTo(getCell(i + 1, j     ));
		// Bottom Middle
		if(inBounds(i     , j + 1)) currCell.connectTo(getCell(i    , j + 1 ));
		// Left Side
		if(inBounds(i - 1 , j    )) currCell.connectTo(getCell(i - 1, j     ));
		
	}
	
	
	public void setNeighbors(SimulationType simType){

		for (int i = 0; i < mySize; i++) {
			for (int j = 0; j < mySize; j++) {
				
				if( simType.equals(SimulationType.GAME_OF_LIFE) ||
						simType.equals(SimulationType.SEGREGATION)) setFullSquareNeighbors(i, j, getCell(i, j));
				
				if( simType.equals(SimulationType.WA_TOR_WORLD) ||
						simType.equals(SimulationType.SPREADING_FIRE)) 
						setSideNeighbors(i, j, getCell(i, j));
				
			}
		}
		
	}
	
	public void setNeighbors(SimulationType simType, BorderType bordType){
		
		setNeighbors(simType);
		
		if(bordType.equals(BorderType.TOROID)) setToroid();
		
	}
}

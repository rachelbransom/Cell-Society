package grid;

import cellUtil.Cell;
import simulation.types.SimulationType;

public abstract class Grid {

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

	public abstract void setFullNeighbors(int i, int j, Cell currCell);
	
	public abstract void setCardinalNeighbors(int i, int j, Cell currCell);
	
	
	public void setNeighbors(SimulationType simType){

		for (int i = 0; i < mySize; i++) {
			for (int j = 0; j < mySize; j++) {
				
				if( simType.equals(SimulationType.GAME_OF_LIFE) ||
						simType.equals(SimulationType.SEGREGATION)) setFullNeighbors(i, j, getCell(i, j));
				
				if( simType.equals(SimulationType.WA_TOR_WORLD) ||
						simType.equals(SimulationType.SPREADING_FIRE)) 
						setCardinalNeighbors(i, j, getCell(i, j));
				
			}
		}
	}
}

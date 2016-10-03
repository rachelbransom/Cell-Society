package grid;

import cell.Cell;

/**
 * 
 * @author Diane Hadley
 *
 */


public abstract class Neighbors{
	
	protected Grid myGrid;
	private int mySize;
	
	public Neighbors(Grid grid, int size){
		myGrid = grid;
		mySize = size;
	}
	
	public boolean inBounds(int x, int y){
		boolean inX = 0 <= x && x < mySize;
		boolean inY = 0 <= y && y < mySize;
				
		return inX && inY;
	}
	
	public abstract void setFullNeighbors(int i, int j, Cell currCell);
	
	public abstract void setCardinalNeighbors(int i, int j, Cell currCell);
	
}
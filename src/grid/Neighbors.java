package grid;

import cell.Cell;

public abstract class Neighbors{
	
	public abstract void setFullNeighbors(int i, int j, Cell currCell);
	
	public abstract void setCardinalNeighbors(int i, int j, Cell currCell);
	
	
	
	
}
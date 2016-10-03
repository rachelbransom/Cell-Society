package grid;

import grid.BorderType;
import cell.Cell;
import simulation.types.SimulationType;


public class Grid {

	private Cell[][] myCellGrid;
	private int mySize;
	private Neighbors myNeighbors;
	private String shape;

	public Grid(int n, String shape){
		this.shape = shape;
		mySize = n;
		myCellGrid = new Cell[mySize][mySize];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				myCellGrid[i][j] = new Cell(i,j);
			}		}
	}
	
	public Grid( Grid that ){
		
		this.mySize = that.mySize;
		this.shape = that.shape;
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
	
	public String getShape(){
		return shape;
	}
	
	public Cell getCell(int x, int y){
		return myCellGrid[x][y];
	}
		
	public void setCell(int x, int y, Cell input){
		myCellGrid[x][y] = new Cell(input);
	}
	
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

	
	
	
	private void getNeighborsType(){
		switch(shape) {
		case("Square"):
			myNeighbors = new EightNeighbors(this, mySize);
		case("Triangle"):
			myNeighbors = new EightNeighbors(this, mySize);
		case("Hexagon"):
			myNeighbors = new SixNeighbors(this, mySize);
		}
}
	
	
	
	public void setNeighbors(SimulationType simType){
		getNeighborsType();
		for (int i = 0; i < mySize; i++) {
			for (int j = 0; j < mySize; j++) {
				
				if( simType.equals(SimulationType.GAME_OF_LIFE) ||
					simType.equals(SimulationType.SEGREGATION)  ||
					simType.equals(SimulationType.SLIME_MOLD)   ||
					simType.equals(SimulationType.SUGARSCAPE) ) 
					myNeighbors.setFullNeighbors(i, j, getCell(i, j));
				
				if( simType.equals(SimulationType.WA_TOR_WORLD) ||
						simType.equals(SimulationType.SPREADING_FIRE)) 
					myNeighbors.setCardinalNeighbors(i, j, getCell(i, j));
				
			}
		}
	}
	
	
	
	
	public void setNeighbors(SimulationType simType, BorderType bordType){
		
		setNeighbors(simType);
		
		if(bordType.equals(BorderType.TOROID)) setToroid();
		
	}

}

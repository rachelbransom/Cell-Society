package simulation.types.hierarchy;

import java.util.HashMap;
import java.util.Map;


import cell.Cell;

import grid.Grid;
import javafx.scene.paint.Color;

/**
 *	This class manages the current state of the grid. 
 *	
 *	@author George Bernard and Rachel Bransom
 *
 */
public abstract class AbstractSimulation {

	private Grid myCurrGrid;
	private Grid myNextGrid;
	private int mySize;
	
	public AbstractSimulation( Grid inputGrid ){		
		mySize = inputGrid.getSize();
		myCurrGrid = inputGrid;
	}
	
	/*----------------- Abstract Methods -----------------------------*/	
	
	/**
	 * Updates cell according to whichever rules the simulation is 
	 * currently using
	 * 
	 * Preserves all state of cell other than the Actor subclass
	 * 
	 * @param curr Cell to be updated to next state.
	 */
	protected abstract void updateCell(Cell curr);
		
	/*----------------- Implemented methods -----------------------------*/
	
	/**
	 * Updates the grid to the next logical state.
	 * 
	 * In Implementation should make copies of grid and use the below 
	 * updateCell method on every Cell in the grid.
	 * 
	 * @param 	grid  Reference to current Grid
	 * @return		  Reference to the next Grid
	 */
	protected void updateGrid(){

		// Make copies of input Grid
		myNextGrid  = new Grid(myCurrGrid.getSize());
		
		// Update each cell
		for (int i = 0; i < myCurrGrid.getSize(); i++) {
			for (int j = 0; j < myCurrGrid.getSize(); j++) {
				updateCell(myCurrGrid.getCell(i, j));
			}
		}
		
		myCurrGrid = new Grid( myNextGrid );
	};

	protected Grid getCurrGrid(){
		return myCurrGrid;
	}
	
	protected Grid getNextGrid(){
		return myNextGrid;
	}
	
	protected int getSize(){
		return mySize;
	}
	
	public Grid showCurrGrid(){
		return new Grid(myCurrGrid);
	}
	
	public Grid showNextGrid(){
		updateGrid();
		return showCurrGrid();
	}
}
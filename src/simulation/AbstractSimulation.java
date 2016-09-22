package simulation;

import java.util.Map;

import cellUtil.Cell;
import cellUtil.Grid;
import javafx.scene.paint.Color;

/**
 * 
 *
 *	This class manages the current state of the grid. 
 *	Responsibility for returning 
 *
 *	Provides two relevant methods: getNextGridState
 *	
 *	@author George Bernard
 *
 */
public abstract class AbstractSimulation {

	protected Grid myCurrGrid;
	protected Grid myNextGrid;
	protected int mySize;
	protected Map<Enum, Color> myColorMap;
	
	AbstractSimulation( Grid inputGrid ){
		
		mySize = inputGrid.getSize();
		myCurrGrid = new Grid( inputGrid );
		
		initColorMap();
	}
	
	Grid showGrid(){
		return new Grid( myCurrGrid );
	}
	
	/**
	 * Updates the grid to the next logical state.
	 * 
	 * In Implementation should make copies of grid and use the below 
	 * updateCell method on every Cell in the grid.
	 * 
	 * @param 	grid  Reference to current Grid
	 * @return		  Reference to the next Grid
	 */
	
	void updateGrid(){

		// Make copies of input Grid
		myCurrGrid = new Grid(mySize);
		myNextGrid  = new Grid(mySize);
		
		// Update each cell
		for (int i = 0; i < myCurrGrid.getSize(); i++) {
			for (int j = 0; j < myCurrGrid.getSize(); j++) {
				updateCell(myCurrGrid.getCell(i, j));
			}
		}
		
		myCurrGrid = myNextGrid;
	};
	
	/**
	 * Updates cell according to whichever rules the simulation is 
	 * currently using
	 * 
	 * Preserves all state of cell other than the Actor subclass
	 * 
	 * @param curr Cell to be updated to next state.
	 */
	protected abstract void updateCell(Cell curr);
	
	protected abstract void initColorMap();
	
	Map<Enum, Color> getColorMap(){
		return myColorMap;
	};

}
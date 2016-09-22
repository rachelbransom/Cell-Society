package logic;

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
	protected Grid myNewGrid;
	
	
	/**
	 * Returns the next holistic state of the Grid. I.e. returns a Grid 
	 * of the next logical state after the input Grid. 
	 * 
	 * In Implementation should make copies of grid and use the below 
	 * updateCell method on every Cell in the grid.
	 * 
	 * @param 	grid  Reference to current Grid
	 * @return		  Reference to the next Grid
	 */
	abstract void updateGrid();
	
	/**
	 * Updates cell according to whichever rules the simulation is 
	 * currently using
	 * 
	 * Preserves all state of cell other than the Actor subclass
	 * 
	 * @param curr Cell to be updated to next state.
	 */
	protected abstract void updateCell(Cell curr);
	
}

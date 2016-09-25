package simulation.types;

import java.util.Map;

import cellUtil.Actor;
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
	
	public AbstractSimulation( Grid inputGrid ){
		
		mySize = inputGrid.getSize();
		myCurrGrid = inputGrid;
		
		initColorMap();
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
	
	protected abstract void initColorMap();
	
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
	void updateGrid(){

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
	
	
	
	
	Map<Enum, Color> getColorMap(){
		return myColorMap;
	};

	public Color[][] showColorGrid() {
		updateGrid();
		
		Color[][] colorGrid = new Color[mySize][mySize];
		
		for (int i = 0; i < mySize; i++) {
			for (int j = 0; j < mySize; j++) {
				
				Actor currActor = myCurrGrid.getCell(i, j).getActor();
				colorGrid[i][j] = myColorMap.get(currActor.getState());
			
			}
		}
		
		return colorGrid;
	}
	
	
}
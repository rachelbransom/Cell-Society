package simulation.types;

import java.util.Map;

import cellUtil.Actor;
import cellUtil.Cell;
import cellUtil.Grid;
import javafx.scene.chart.LineChart;
import javafx.scene.paint.Color;

/**
 *	This class manages the current state of the grid. 
 *	
 *	@author George Bernard and Rachel Bransom
 *
 */
public abstract class AbstractSimulation {

	protected Grid myCurrGrid;
	protected Grid myNextGrid;
	protected int mySize;
	protected Map<Enum, Color> myColorMap;
	protected Map<Color, Integer> myPopulationMap;
	protected LineChart<Number, Number> lineChart;
	
	public AbstractSimulation( Grid inputGrid ){
		
		mySize = inputGrid.getSize();
		myCurrGrid = inputGrid;
		
		initColorMap();
		initPopulationMap();
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
	
	protected abstract void initPopulationMap();
	
	
	
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
	
	protected void update(){
		updateAndChangeGrid();
	}
	
	protected void updateGrid(){

		// Update each cell
		for (int i = 0; i < myCurrGrid.getSize(); i++) {
			for (int j = 0; j < myCurrGrid.getSize(); j++) {
				updateCell(myCurrGrid.getCell(i, j));
			}
		}
		
	};
	
	protected void updateAndChangeGrid() {
		myNextGrid = new Grid(myCurrGrid.getSize());
		updateGrid();
		myCurrGrid = new Grid( myNextGrid );
		
	}


	public Color[][] showNextColorGrid(){
		updateGrid();	
		return showCurrColorGrid();
	}
	
	public Color[][] showCurrColorGrid() {
		
		Color[][] colorGrid = new Color[mySize][mySize];
		
		for (int i = 0; i < mySize; i++) {
			for (int j = 0; j < mySize; j++) {
				
				Actor currActor = myCurrGrid.getCell(i, j).getActor();
				colorGrid[i][j] = myColorMap.get(currActor.getState());
				
			}
		}
		
		return colorGrid;
	}
	
	public Map<Color,Integer> getPopuationMap(){
		return myPopulationMap;
	}

	public LineChart<Number, Number> getMyChart() {
		return lineChart;
	}
	/*----------------- Overriden Methods -----------------------------*/
	
	
}
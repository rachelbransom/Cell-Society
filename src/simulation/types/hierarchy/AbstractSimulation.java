package simulation.types.hierarchy;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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

	protected LineChart<Number, Number> lineChart;
	private Grid myCurrGrid;
	protected Grid myNextGrid;
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
	

	protected void initPopulationGraphSuper(){
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		
		xAxis.setLabel("Time");
		yAxis.setLabel("Alive");
		xAxis.setForceZeroInRange(false);
		xAxis.setAutoRanging(false);
		lineChart = new LineChart<Number, Number>(xAxis, yAxis);
		lineChart.setMaxHeight(250);
		lineChart.setPrefWidth(505);
	}

//	public LineChart<Number, Number> getMyChart() {
//		return lineChart;
//	}
	
//	public void setMyChartLayout(int x, int y){
//		lineChart.setLayoutX(x);
//		lineChart.setLayoutY(y);
//	}
	/*----------------- Overriden Methods -----------------------------*/
	
	
	public Grid showCurrGrid(){
		return new Grid(myCurrGrid);
	}
	
	public Grid showNextGrid(){
		updateGrid();
		return showCurrGrid();
	}
}
package simulation.types;

import java.util.Map;

import cellUtil.Actor;
import cellUtil.Cell;
import cellUtil.Grid;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
	protected LineChart<Number, Number> lineChart;
	
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

	public LineChart<Number, Number> getMyChart() {
		return lineChart;
	}
	
	public void setMyChartLayout(int x, int y){
		lineChart.setLayoutX(x);
		lineChart.setLayoutY(y);
	}
	/*----------------- Overriden Methods -----------------------------*/
	
	
}
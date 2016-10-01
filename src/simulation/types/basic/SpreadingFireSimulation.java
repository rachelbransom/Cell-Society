package simulation.types.basic;

import java.awt.Point;
import java.util.Random;

import javafx.scene.chart.XYChart;
import cell.Actor;
import cell.BorderType;
import cell.Cell;
import cell.CellState.SpreadingFire;
>>>>>>> 8c7f8b03adcacf4ed37493f9bbd99b49dd48af08
import javafx.scene.paint.Color;
import simulation.types.SimulationType;


import simulation.types.hierarchy.AbstractSimulation;
import grid.Grid;


public class SpreadingFireSimulation extends AbstractSimulation {
	private int tree, burning, counter;
	private double myProbCatch;
	private Random myRandom;

	public SpreadingFireSimulation( Grid inputGrid , double probCatch){
		super( inputGrid );

		initPopulationCounts(inputGrid);
		getCurrGrid().setNeighbors(SimulationType.SPREADING_FIRE, BorderType.TOROID);
		myProbCatch = probCatch;
		myRandom = new Random();
		initPopulationGraph();
	}

	/*----------------- Overridden Methods -----------------------------*/

	@Override
	public void updateGrid(){
		super.updateGrid();
		counter++;
		this.updateChart();
		getCurrGrid().setNeighbors(SimulationType.SPREADING_FIRE, BorderType.TOROID);
	}

	@Override
	protected void updateCell(Cell currCell) {

		// Set intermediate Variables
		SpreadingFire currState = (SpreadingFire) currCell.getActor().getState();

		Point location = currCell.getLocation();
		Cell newCell = new Cell(currCell);

		// If cell is empty or on fire, next turn it's 
		if( currState == SpreadingFire.EMPTY || currState == SpreadingFire.BURNING ){
			if (currState.equals(SpreadingFire.BURNING)){
				burning--;
			}
			newCell.setActor( new Actor(SpreadingFire.EMPTY) );
		}

		// If cell has a tree then check for chance of burning from neighbors
		if( currState == SpreadingFire.TREE )
			for (Cell neighbor : currCell.getNeighbors())
				if ( neighbor.getActor().getState().equals(SpreadingFire.BURNING) && myRandom.nextDouble() < myProbCatch ) {
					burning++;
					newCell.setActor( new Actor(SpreadingFire.BURNING) );
				}

		getNextGrid().setCell(location.x, location.y, newCell);
	}


	@Override
	protected void initColorMap() {
		myColorMap = new HashMap<Enum, Color>();		

		myColorMap.put(SpreadingFire.EMPTY, Color.YELLOW);
		myColorMap.put(SpreadingFire.TREE, Color.GREEN);
		myColorMap.put(SpreadingFire.BURNING, Color.RED);

	}
	
//	private void initPopulationCounts(Grid grid){
//		for (int i=0; i<grid.getSize(); i++){
//			for (int j=0; j<grid.getSize(); j++){
//				switch ( (SpreadingFire) grid.getCell(i, j).getActor().getState() ){
//					
//				}
//			}
//		}
//	}
	
	private void initPopulationGraph(){
		tree = 0;
		burning = 0;
		super.initPopulationGraphSuper();
		
		XYChart.Series series1 = new XYChart.Series<>();
		XYChart.Series series2 = new XYChart.Series<>();
		series1.getData().add(new XYChart.Data<Number, Number>(0, 0));
		series2.getData().add(new XYChart.Data<Number, Number>(0, 0));
		lineChart.getData().addAll(series1, series2);
	}
	
	private void updateChart() {
		XYChart.Series series1 = new XYChart.Series<>();
		XYChart.Series series2 = new XYChart.Series<>();
		lineChart.getData().get(0).getData().addAll(new XYChart.Data(counter, tree),
				new XYChart.Data(counter, burning));
	}

}


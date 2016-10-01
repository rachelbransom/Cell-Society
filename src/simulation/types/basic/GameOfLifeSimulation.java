package simulation.types.basic;
import javafx.scene.chart.*;
import java.awt.Point;
import java.util.HashMap;

import cellUtil.Actor;
import cellUtil.BorderType;
import cellUtil.Cell;
import cellUtil.CellState.GameOfLife;
import cellUtil.Grid;
import javafx.scene.paint.Color;
import simulation.types.AbstractSimulation;
import simulation.types.SimulationType;

public class GameOfLifeSimulation extends AbstractSimulation {
	private int myAliveCells;
	private int myDeadCells;
	private int counter;
	public GameOfLifeSimulation(Grid grid) {
		super(grid);
		myCurrGrid.setNeighbors(SimulationType.GAME_OF_LIFE, BorderType.TOROID);
		myAliveCells = 0;
		myDeadCells = 0;
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Time");
		yAxis.setLabel("Alive");
		lineChart = new LineChart<Number, Number>(xAxis, yAxis);
		XYChart.Series series = new XYChart.Series<>();
		series.getData().add(new XYChart.Data<Number, Number>(0, 0));
		lineChart.getData().add(series);
	}

	
	@Override
	protected void updateGrid() {
		super.updateGrid();
		myCurrGrid.setNeighbors(SimulationType.GAME_OF_LIFE, BorderType.TOROID);
		counter++;
		this.updateChart();
		
	}

	@Override
	protected void updateCell(Cell currCell) {

		GameOfLife currState = (GameOfLife) currCell.getActor().getState();
		Point location = currCell.getLocation();
		Cell newCell = new Cell(currCell);
		int numAliveNeighbors = currCell.numberNeighborsWithState(GameOfLife.ALIVE);

		
		if (currState.equals(GameOfLife.ALIVE)) {
			// Cell dies
			if (numAliveNeighbors < 2 || numAliveNeighbors > 3) {
				newCell.getActor().changeState(GameOfLife.DEAD);
				myDeadCells++;
				myAliveCells--;
			}
			// Cell Stays Alive
			else {
				newCell.setActor(new Actor(GameOfLife.ALIVE));
				
			}
		}

		// If Cell is Dead
		if (currState == GameOfLife.DEAD) {
			// Cell repopulates
			if (numAliveNeighbors == 3){
				newCell.getActor().changeState(GameOfLife.ALIVE);
				myAliveCells++;
				myDeadCells--;
			}
			// Cell Stays Dead
			else
				newCell.setActor(new Actor(GameOfLife.DEAD));
		}

		myNextGrid.setCell(location.x, location.y, newCell);

	}

	@Override
	protected void initColorMap() {
		myColorMap = new HashMap<Enum, Color>();

		myColorMap.put(GameOfLife.DEAD, Color.WHITE);
		myColorMap.put(GameOfLife.ALIVE, Color.BLACK);

	}

	protected void initPopulationMap() {
		myPopulationMap = new HashMap<Color, Integer>();

		myPopulationMap.put(Color.WHITE, 0);
		myPopulationMap.put(Color.BLACK, 0);

	}
	
	

	/*----------------- Helper / Private Methods -----------------------------*/
	private void updateChart(){
		XYChart.Series series = new XYChart.Series<>();
		lineChart.getData().get(0).getData().add(new XYChart.Data(counter, myAliveCells));

	}
}

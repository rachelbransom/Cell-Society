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
	private int counter;

	public GameOfLifeSimulation(Grid grid) {
		super(grid);
		myCurrGrid.setNeighbors(SimulationType.GAME_OF_LIFE, BorderType.TOROID);
		initPopulationGraph();
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
				kill(newCell);
			}
			// Cell Stays Alive
			else {
				stayAlice(newCell);
			}
		}

		if (currState == GameOfLife.DEAD) {
			if (numAliveNeighbors == 3) {
				birth(newCell);
			}
			// Cell Stays Dead
			else
				stayDead(newCell);
		}

		myNextGrid.setCell(location.x, location.y, newCell);

	}

	private void kill(Cell newCell) {
		newCell.getActor().changeState(GameOfLife.DEAD);
		myAliveCells--;
	}

	private void birth(Cell newCell) {
		newCell.getActor().changeState(GameOfLife.ALIVE);
		myAliveCells++;
	}

	private void stayDead(Cell newCell) {
		newCell.setActor(new Actor(GameOfLife.DEAD));
	}

	private void stayAlice(Cell newCell) {
		newCell.setActor(new Actor(GameOfLife.ALIVE));
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

	private void initPopulationGraph() {
		myAliveCells = 0;
		// myDeadCells = 0;

		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();

		xAxis.setLabel("Time");
		yAxis.setLabel("Alive");
		
		xAxis.setForceZeroInRange(false);
		xAxis.setAutoRanging(false);
		lineChart = new LineChart<Number, Number>(xAxis, yAxis);
		XYChart.Series series = new XYChart.Series<>();
		series.getData().add(new XYChart.Data<Number, Number>(0, 0));
		lineChart.getData().add(series);
	}

	/*----------------- Helper / Private Methods -----------------------------*/

	private void updateChart() {
		XYChart.Series series = new XYChart.Series<>();
		lineChart.getData().get(0).getData().add(new XYChart.Data(counter, myAliveCells));

	}
}

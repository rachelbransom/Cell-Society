package simulation.types.basic;

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

	public GameOfLifeSimulation(Grid grid){
		super(grid);
		myCurrGrid.setNeighbors(SimulationType.GAME_OF_LIFE, BorderType.TOROID);
	}

	/*----------------- Overriden Methods -----------------------------*/

	@Override
	protected void updateGrid(){
		super.updateGrid();
		myCurrGrid.setNeighbors(SimulationType.GAME_OF_LIFE, BorderType.TOROID);
	}

	@Override
	protected void updateCell(Cell currCell) {

		GameOfLife currState = (GameOfLife) currCell.getActor().getState();
		Point location = currCell.getLocation();
		Cell newCell = new Cell( currCell );
		int numAliveNeighbors = currCell.numberNeighborsWithState(GameOfLife.ALIVE);
		
		// If Cell is alive
		if( currState.equals(GameOfLife.ALIVE) ) {
			// Cell dies
			if( numAliveNeighbors < 2 || numAliveNeighbors > 3) newCell.getActor().changeState(GameOfLife.DEAD);
			// Cell Stays Alive
			else 												newCell.setActor( new Actor(GameOfLife.ALIVE) );
		}
		
		// If Cell is Dead
		if(currState == GameOfLife.DEAD){
			// Cell repopulates
			if( numAliveNeighbors == 3) newCell.getActor().changeState(GameOfLife.ALIVE);
			// Cell Stays Dead
			else						newCell.setActor( new Actor(GameOfLife.DEAD) );
		}
		
		myNextGrid.setCell(location.x, location.y, newCell );		

	}

	@Override
	protected void initColorMap() {
		myColorMap = new HashMap<Enum, Color>();

		myColorMap.put(GameOfLife.DEAD, Color.WHITE);
		myColorMap.put(GameOfLife.ALIVE, Color.BLACK);

	}
	
	/*----------------- Helper / Private Methods -----------------------------*/


}

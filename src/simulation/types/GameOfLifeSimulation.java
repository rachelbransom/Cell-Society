package simulation.types;

import java.awt.Point;
import java.util.HashMap;

import cellUtil.Actor;
import cellUtil.Cell;
import cellUtil.CellState.GameOfLife;
import cellUtil.Grid;
import javafx.scene.paint.Color;

public class GameOfLifeSimulation extends AbstractSimulation {

	public GameOfLifeSimulation(Grid grid){
		super(grid);
		myCurrGrid.setNeighbors(SimulationType.GAME_OF_LIFE);
	}

	/*----------------- Overriden Methods -----------------------------*/

	@Override
	protected void updateGrid(){
		super.updateGrid();
		myCurrGrid.setNeighbors(SimulationType.GAME_OF_LIFE);
	}

	@Override
	protected void updateCell(Cell currCell) {

		GameOfLife currState = (GameOfLife) currCell.getActor().getState();

		Point location = currCell.getLocation();
		Cell newCell = new Cell( currCell );
		
		if( currState.equals(GameOfLife.ALIVE) ) {
			// Cell dies
			if( numberNeighborsWithState(GameOfLife.ALIVE, currCell) < 2 || 
					numberNeighborsWithState(GameOfLife.ALIVE, currCell) > 3){
				newCell.getActor().changeState(GameOfLife.DEAD);
			}
			// Cell Stays Alive
			else{
				newCell.setActor( new Actor(GameOfLife.ALIVE) );
			}
		}
		if(currState == GameOfLife.DEAD){
			// Cell repopulates
			if(numberNeighborsWithState(GameOfLife.ALIVE, currCell) == 3)
				newCell.getActor().changeState(GameOfLife.ALIVE);
			else{
				newCell.setActor( new Actor(GameOfLife.DEAD) );
			}
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

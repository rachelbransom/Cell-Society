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
			if( numberAliveNeighbors(newCell) < 2 || numberAliveNeighbors(newCell) > 3){
				newCell.setActor( new Actor(GameOfLife.DEAD) );
			}
			// Cell Stays Alive
			else{
				newCell.setActor( new Actor(GameOfLife.ALIVE) );
			}
		}
		
		if( currState.equals(GameOfLife.DEAD) ){
			// Cell Repopulates
			if(numberAliveNeighbors(newCell) == 3){
				newCell.setActor( new Actor(GameOfLife.ALIVE) ); 
			}
			// Cell Stays Dead
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

	private int numberAliveNeighbors(Cell cell){
		int count = 0;

		for (Cell neighbor : cell.getNeighbors()){
			if(neighbor.getActor().getState().equals(GameOfLife.ALIVE)){
				count++;
			}
		}		
		return count;
	}

}

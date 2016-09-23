package simulation.types;

import java.awt.Point;
import java.util.HashMap;

import cellUtil.Cell;
import cellUtil.CellState.GameOfLife;
import cellUtil.Grid;
import javafx.scene.paint.Color;

public class GameOfLifeSimulation extends AbstractSimulation {

	public GameOfLifeSimulation(Grid grid){
		super(grid);
	}
	
	/*----------------- Overriden Methods -----------------------------*/
	
	@Override
	public Color[][] showColorGrid() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected void updateCell(Cell currCell) {
		// TODO Auto-generated method stub

		GameOfLife currState = (GameOfLife) currCell.getActor().getState();
		
		Point location = currCell.getLocation();
		Cell newCell = myNextGrid.getCell(location.x, location.y);
		

		if( currState == GameOfLife.ALIVE ) {;
			// Cell dies
			if( numberAliveNeighbors(currCell) < 2 || 
					numberAliveNeighbors(currCell) > 3){
				newCell.getActor().changeState(GameOfLife.DEAD);
			}
			// Cell Stays Alive
			else{
				newCell.getActor().changeState(GameOfLife.ALIVE);
			}
		}
		if(currState == GameOfLife.DEAD){
			// Cell repopulates
			if(numberAliveNeighbors(currCell) == 3)
				newCell.getActor().changeState(GameOfLife.ALIVE);
			else{
				newCell.getActor().changeState(GameOfLife.DEAD);
			}
		}
	}

	private int numberAliveNeighbors(Cell cell){
		int count = 0;

		for (Cell neighbor : cell.getNeighbors())
			if(neighbor.getActor().getState() == GameOfLife.ALIVE)
				count++;

		return count;
	}
	
	@Override
	protected void initColorMap() {
		myColorMap = new HashMap<Enum, Color>();
		
		myColorMap.put(GameOfLife.DEAD, Color.WHITE);
		myColorMap.put(GameOfLife.ALIVE, Color.BLACK);

	}
	
	
}

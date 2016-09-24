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

		GameOfLife currState = (GameOfLife) currCell.getActor().getState();
		
		Point location = currCell.getLocation();
		Cell newCell = myNextGrid.getCell(location.x, location.y);
		

		if( currState == GameOfLife.ALIVE ) {;
			// Cell dies
			if( numberNeighborsWithState(GameOfLife.ALIVE, currCell) < 2 || 
					numberNeighborsWithState(GameOfLife.ALIVE, currCell) > 3){
				newCell.getActor().changeState(GameOfLife.DEAD);
			}
			// Cell Stays Alive
			else{
				newCell.getActor().changeState(GameOfLife.ALIVE);
			}
		}
		if(currState == GameOfLife.DEAD){
			// Cell repopulates
			if(numberNeighborsWithState(GameOfLife.ALIVE, currCell) == 3)
				newCell.getActor().changeState(GameOfLife.ALIVE);
			else{
				newCell.getActor().changeState(GameOfLife.DEAD);
			}
		}
	}

	
	
	@Override
	protected void initColorMap() {
		myColorMap = new HashMap<Enum, Color>();
		
		myColorMap.put(GameOfLife.DEAD, Color.WHITE);
		myColorMap.put(GameOfLife.ALIVE, Color.BLACK);

	}
	
	public static void main(String[] args){
		int size = 3;
		
		Grid g = new Grid(size);
		Cell[] arr = new Cell[size];
		
		// set middle column to alive, rest to dead
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				g.setCell(i, j, new Cell(i, j));
				if(i == 1) g.getCell(i, j).getActor().changeState(GameOfLife.DEAD);
				else g.getCell(i, j).getActor().changeState(GameOfLife.ALIVE);
			}
		}
		
		GameOfLifeSimulation sim = new GameOfLifeSimulation(g); 
		
		for(int run = 0; run < 5; run++){
			
			sim.updateGrid();
			
			Grid printGrid = sim.myCurrGrid;
			
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr.length; j++) {
					
					
					
				}
			}
		}
		
	}
	
	
}

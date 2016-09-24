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
	public void updateGrid(){
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
				newCell.newActorWithState(GameOfLife.DEAD);
			}
			// Cell Stays Alive
			else{
				newCell.newActorWithState(GameOfLife.ALIVE);
			}
		}
		
		if( currState.equals(GameOfLife.DEAD) ){
			// Cell Repopulates
			if(numberAliveNeighbors(newCell) == 3){
				newCell.newActorWithState(GameOfLife.ALIVE); 
			}
			// Cell Stays Dead
			else{
				newCell.newActorWithState(GameOfLife.DEAD);
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
 
	/*
	public static void main(String[] args){
		int size = 3;

		Grid g = new Grid(size);
		Cell[] arr = new Cell[size];

		// set middle column to alive, rest to dead
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				g.setCell(i, j, new Cell(i, j));
				if (i == 1) g.getCell(i, j).getActor().changeState(GameOfLife.ALIVE);
				else g.getCell(i, j).getActor().changeState(GameOfLife.DEAD);
			}
		}

		GameOfLifeSimulation sim = new GameOfLifeSimulation(g); 

		for(int run = 0; run < 5; run++){

			Grid printGrid = sim.myCurrGrid;

			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr.length; j++) {

					Actor printActor = printGrid.getCell(i, j).getActor();

					if(printActor.getState().equals(GameOfLife.ALIVE)) System.out.print(" # |");
					if(printActor.getState().equals(GameOfLife.DEAD)) System.out.print("   |");
				}


				System.out.println("");
			}

			sim.updateGrid();

			System.out.println("");
		}

	}
	*/
}

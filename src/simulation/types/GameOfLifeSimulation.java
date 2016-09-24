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
		setSquareNeighbors();
	}

	/*----------------- Overriden Methods -----------------------------*/

	@Override
	public Color[][] showColorGrid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateGrid(){
		super.updateGrid();
		this.setSquareNeighbors();
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

	private void setSquareNeighbors(){

		for (int i = 0; i < myCurrGrid.getSize(); i++) {
			for (int j = 0; j < myCurrGrid.getSize(); j++) {
				Cell currCell = myCurrGrid.getCell(i, j);

				currCell.getNeighbors().clear();

				// Top Left
				if(myCurrGrid.inBounds(i - 1 , j - 1)) currCell.getNeighbors().add(myCurrGrid.getCell(i - 1, j - 1));
				// Top Middle
				if(myCurrGrid.inBounds(i     , j - 1)) currCell.getNeighbors().add(myCurrGrid.getCell(i    , j - 1 ));
				// Top Right
				if(myCurrGrid.inBounds(i + 1 , j - 1)) currCell.getNeighbors().add(myCurrGrid.getCell(i + 1, j - 1));
				// Right Side
				if(myCurrGrid.inBounds(i + 1 , j    )) currCell.getNeighbors().add(myCurrGrid.getCell(i + 1, j    ));
				// Bottom Right
				if(myCurrGrid.inBounds(i + 1 , j + 1)) currCell.getNeighbors().add(myCurrGrid.getCell(i + 1, j + 1));
				// Bottom Middle
				if(myCurrGrid.inBounds(i     , j + 1)) currCell.getNeighbors().add(myCurrGrid.getCell(i    , j + 1));
				// Bottom Left
				if(myCurrGrid.inBounds(i - 1 , j + 1)) currCell.getNeighbors().add(myCurrGrid.getCell(i - 1, j + 1));
				// Left Side
				if(myCurrGrid.inBounds(i - 1 , j    )) currCell.getNeighbors().add(myCurrGrid.getCell(i - 1, j    ));

			}
		}
	}

	private int numberAliveNeighbors(Cell cell){
		int count = 0;

		for (Cell neighbor : cell.getNeighbors()){
			if(neighbor.getActor().getState().equals(GameOfLife.ALIVE)){
				count++;
			}
		}		
		return count;
	}

	@Override
	protected void initColorMap() {
		myColorMap = new HashMap<Enum, Color>();

		myColorMap.put(GameOfLife.DEAD, Color.WHITE);
		myColorMap.put(GameOfLife.ALIVE, Color.BLACK);

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

package simulation.types.advanced;



import java.awt.Point;
import java.util.Collection;

import cell.Actor;
import cell.Cell;
import cell.CellState.Langton;
import grid.Grid;
import simulation.types.SimulationType;
import simulation.types.hierarchy.AbstractSimulation;

/**
 * @author Diane Hadley
 * 
 */



public class LangtonSimulation extends AbstractSimulation {

	public LangtonSimulation(Grid inputGrid) {
		super(inputGrid);
		
		getCurrGrid().setNeighbors(SimulationType.LANGTONS_LOOPS);
	}

	
	/*----------------- Overridden Methods -----------------------------*/
	
	@Override

	protected void updateGrid(){	
		super.updateGrid();
		getCurrGrid().setNeighbors(SimulationType.LANGTONS_LOOPS);
		}
	
	
	@Override
	protected void updateCell(Cell currCell) {
		Langton currState = (Langton) currCell.getActor().getState();
		
		Point location = currCell.getLocation();
		Collection<Cell> neighbors = currCell.getNeighbors();
		Cell newCell = new Cell(currCell);
		
		if (currState == Langton.NOCOMMAND){			
			updateNoCommandCell(currCell, neighbors, newCell);			
		}
		if ( currState == Langton.EMPTY){
			updateEmptyCell(currCell, newCell);
		}		
		if ( currState == Langton.TURN || currState == Langton.ADVANCE){			
			updateCommandCell(currCell, newCell);						
		}		
		if ( currState == Langton.SHEATH ) {
			updateSheathCell(currCell, currState, location, neighbors, newCell);			
		}		
		if ( currState == Langton.MAKETURN) {
			updateMakeTurnCell(currCell, newCell);					
		}		
		if ( currState == Langton.MESSENGER) {
			updateMessengerCell(currCell, newCell);
		}		
		if ( currState == Langton.ENDLOOP ) {
			newCell.setActor( new Actor(Langton.EMPTY) );
		}
		getNextGrid().setCell(location.x, location.y, newCell);
	}


	private void updateMessengerCell(Cell currCell, Cell newCell) {
		if (currCell.numberNeighborsWithState(Langton.SHEATH) == 3) {
			newCell.setActor( new Actor(Langton.EMPTY) );
		}
		else if (currCell.numberNeighborsWithState(Langton.SHEATH) == 2) {
			newCell.setActor( new Actor(Langton.SHEATH) );
		}
	}


	private void updateMakeTurnCell(Cell currCell, Cell newCell) {
		if ( currCell.numberNeighborsWithState(Langton.EMPTY) == 3 ) {
			newCell.setActor( new Actor(Langton.SHEATH) );
		}
		
		else if (currCell.numberNeighborsWithState(Langton.TURN) == 1  ) {
			newCell.setActor( new Actor(Langton.NOCOMMAND) );
		}
		
		else if (currCell.numberNeighborsWithState(Langton.EMPTY) == 2 ) {
			int x1 = 0;
			int x2 = 0;
			int y1 = 0;
			int y2 = 0;
			for (Cell neighbor : currCell.getNeighborsWithState(Langton.NOCOMMAND)) {
				x1 = neighbor.getLocation().x;
				y1 = neighbor.getLocation().y;
			}
			for (Cell neighbor : currCell.getNeighborsWithState(Langton.SHEATH)) {
				x2 = neighbor.getLocation().x;
				y2 = neighbor.getLocation().y;
			}
			
			if (x1 == x2 || y1 == y2) {
				newCell.setActor( new Actor(Langton.NOCOMMAND) );
			}
		}
	}


	private void updateSheathCell(Cell currCell, Langton currState, Point location, Collection<Cell> neighbors,
			Cell newCell) {
		if ( currCell.numberNeighborsWithState(Langton.EMPTY) == 3 &&
			currCell.numberNeighborsWithState(Langton.ADVANCE) == 1 ){
			newCell.setActor( new Actor(Langton.NOCOMMAND) );
		}
		
		else if (currCell.numberNeighborsWithState(Langton.EMPTY) == 2 &&
			currCell.numberNeighborsWithState(Langton.ADVANCE) == 1 &&
			currCell.numberNeighborsWithState(Langton.SHEATH) == 1){
			
			for (Cell neighbor : currCell.getNeighborsWithState(currState)) {
				if (neighbor.numberNeighborsWithState(currState) == 3) {
					newCell.setActor( new Actor(Langton.MAKETURN) );
				}
			}
			
		}
		else if (currCell.numberNeighborsWithState(Langton.SHEATH) == 2 &&
				currCell.numberNeighborsWithState(Langton.MAKETURN) == 1){
			newCell.setActor( new Actor(Langton.NOCOMMAND) );
			
		}		
		else if (((currCell.numberNeighborsWithState(Langton.SHEATH) == 1 &&
				currCell.numberNeighborsWithState(Langton.EMPTY) == 2) ||
				(currCell.numberNeighborsWithState(Langton.SHEATH) == 2 &&
				currCell.numberNeighborsWithState(Langton.EMPTY) == 1)) &&
				currCell.numberNeighborsWithState(Langton.MESSENGER) == 1){
			newCell.setActor( new Actor(Langton.EMPTY) );
		}	
		else {
			for (Cell neighbor : neighbors) {			
				if ( neighbor.getActor().getState().equals(Langton.TURN) ){					
					for (Cell neighbor2 : neighbor.getNeighbors()) {
						if (neighbor2.getActor().getState().equals(Langton.EMPTY)){
							if (neighbor.numberNeighborsWithState(Langton.SHEATH) == 3){
								int i = neighbor2.getLocation().x;
								int j = neighbor2.getLocation().y;
								if ((i < neighbor.getLocation().x && currCell.getLocation().y > neighbor.getLocation().y) ||
										( i > neighbor.getLocation().x && currCell.getLocation().y < neighbor.getLocation().y) ||
										( j > neighbor.getLocation().y && currCell.getLocation().x > neighbor.getLocation().x) ||
										( j < neighbor.getLocation().y && currCell.getLocation().x < neighbor.getLocation().x)) {
									newCell.setActor( new Actor(Langton.MAKETURN) );
								}	
							}							
							else if ( neighbor2.getLocation().x == location.x ||
										neighbor2.getLocation().y == location.y ){
									
								newCell.setActor( new Actor(Langton.MAKETURN) );
							}
						}
					}									
				}	
			}
		}
	}


	private void updateCommandCell(Cell currCell, Cell newCell) {
		if (currCell.numberNeighborsWithState(Langton.SHEATH) == 3){
			newCell.setActor( new Actor(Langton.NOCOMMAND) );
		}
		
		else if (currCell.numberNeighborsWithState(Langton.MESSENGER) == 1){
			newCell.setActor( new Actor(Langton.MESSENGER) );
		}
		
		else {
			newCell.setActor( new Actor(Langton.EMPTY) );
		}
	}


	private void updateEmptyCell(Cell currCell, Cell newCell) {
		if ( currCell.numberNeighborsWithState(Langton.EMPTY) == 0 ){
			newCell.setActor( new Actor(Langton.NOCOMMAND) );
		}
		else if ( currCell.numberNeighborsWithState(Langton.NOCOMMAND) == 1){
			if ( currCell.numberNeighborsWithState(Langton.EMPTY) == 1){
				newCell.setActor( new Actor(Langton.MESSENGER) );
			}
			else {
				newCell.setActor( new Actor(Langton.SHEATH) );
			}
			
		}
		
		else if ( currCell.numberNeighborsWithState(Langton.EMPTY) == 1 &&
				currCell.numberNeighborsWithState(Langton.MAKETURN) == 1){
			newCell.setActor( new Actor(Langton.SHEATH) );
		}
		else if ( currCell.numberNeighborsWithState(Langton.MESSENGER) == 1 &&
				currCell.numberNeighborsWithState(Langton.SHEATH) == 2){
			newCell.setActor( new Actor(Langton.MESSENGER ));
		}
		
	}


	private void updateNoCommandCell(Cell currCell, Collection<Cell> neighbors, Cell newCell) {
		moveCommandCellsAroundLoop(neighbors, newCell);	
		if (currCell.numberNeighborsWithState(Langton.EMPTY) == 1 &&
			currCell.numberNeighborsWithState(Langton.SHEATH) == 1 &&
			currCell.numberNeighborsWithState(Langton.ADVANCE) == 1 &&
			currCell.numberNeighborsWithState(Langton.NOCOMMAND) ==1){
			newCell.setActor( new Actor(Langton.EMPTY) );
		}
		else if (currCell.numberNeighborsWithState(Langton.EMPTY) == 2 &&
				currCell.numberNeighborsWithState(Langton.SHEATH) == 2){
			newCell.setActor( new Actor(Langton.ENDLOOP) );
		}
		
		else if (currCell.numberNeighborsWithState(Langton.ENDLOOP) == 1){
			newCell.setActor( new Actor(Langton.ENDLOOP) );
		}
		else if ( currCell.numberNeighborsWithState(Langton.MESSENGER) == 1 &&
				currCell.numberNeighborsWithState(Langton.SHEATH) == 2){
			newCell.setActor( new Actor(Langton.MESSENGER ));
		}
	}


	private void moveCommandCellsAroundLoop(Collection<Cell> neighbors, Cell newCell) {
		for (Cell neighbor : neighbors) {
			if ( neighbor.getActor().getState().equals(Langton.ADVANCE) ){					
				newCell.setActor( new Actor(Langton.ADVANCE) );
			}
		
			if ( neighbor.getActor().getState().equals(Langton.TURN) ){					
				newCell.setActor( new Actor(Langton.TURN) );			
			}
		}
	}

}

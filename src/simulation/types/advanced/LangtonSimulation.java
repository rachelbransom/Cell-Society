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

	@Override
	protected void updateCell(Cell currCell) {
		Langton currState = (Langton) currCell.getActor().getState();
		
		Point location = currCell.getLocation();
		Collection<Cell> neighbors = currCell.getNeighbors();
		Cell newCell = new Cell(currCell);
		
		
		if (currState == Langton.NOCOMMAND){			
			for (Cell neighbor : neighbors) {
				if ( neighbor.getActor().getState().equals(Langton.ADVANCE) ){					
					newCell.setActor( new Actor(Langton.ADVANCE) );
				}
			
				if ( neighbor.getActor().getState().equals(Langton.TURN) ){					
					newCell.setActor( new Actor(Langton.TURN) );			
				}
			}						
		}
		
		if ( currState == Langton.EMPTY && currCell.numberNeighborsWithState(Langton.EMPTY) == 0 ){
			newCell.setActor( new Actor(Langton.NOCOMMAND) );
		}
		
		
		if ( currState == Langton.TURN || currState == Langton.ADVANCE){
			
			if (currCell.numberNeighborsWithState(Langton.SHEATH) == 3){
				newCell.setActor( new Actor(Langton.NOCOMMAND) );
			}
			
			else {
				newCell.setActor( new Actor(Langton.EMPTY) );
			}						
		}
		
		if ( currState == Langton.SHEATH && 
				currCell.numberNeighborsWithState(Langton.EMPTY) == 3 &&
				currCell.numberNeighborsWithState(Langton.ADVANCE) == 1){
			newCell.setActor( new Actor(Langton.NOCOMMAND) );
		}
		
		
		getNextGrid().setCell(location.x, location.y, newCell);
		

	}

}

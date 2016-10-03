package simulation.types.hierarchy;

import java.util.HashSet;
import java.util.Set;

import cell.Actor;
import cell.Cell;
import grid.Grid;

public abstract class AbstractSequentialSimulation extends AbstractSimulation{

	private Set<Actor> myVisitedActors;
	
	public AbstractSequentialSimulation(Grid inputGrid) {
		super(inputGrid);
		myVisitedActors = new HashSet<>();
	}

	@Override
	protected void updateGrid(){
		// Update each cell, instead of in parrallel
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				Cell currCell = getCurrGrid().getCell(i, j);
				if(!myVisitedActors.contains(currCell.getActor()))
					updateCell(getCurrGrid().getCell(i, j));
				myVisitedActors.add(currCell.getActor());
			}
		}
		myVisitedActors = new HashSet<>();
	}
}

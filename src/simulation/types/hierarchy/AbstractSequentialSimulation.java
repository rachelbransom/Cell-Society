package simulation.types.hierarchy;

import grid.Grid;

public abstract class AbstractSequentialSimulation extends AbstractSimulation{

	public AbstractSequentialSimulation(Grid inputGrid) {
		super(inputGrid);
	}

	@Override
	protected void updateGrid(){
		// Update each cell, instead of in parrallel
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				updateCell(getCurrGrid().getCell(i, j));
			}
		}
	}
}

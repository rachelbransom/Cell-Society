package logic;

/**
 * 
 * 
 *	This class extends AbstractGridManager, but does nothing
 *	
 *	Never Instantiate this class, only added to reduce code 
 *	for getNextGridState method to be extended by managers following
 *	specific rules
 *
 *	@author George Bernard
 */

class Simulation extends AbstractSimulation {
	
	int size;
	
	@Override
	void updateGrid() {
		
		// Make copies of input Grid
		myCurrGrid = new Grid(size);
		myNewGrid  = new Grid(size);
		
		// Update each cell
		for (int i = 0; i < myCurrGrid.getSize(); i++) {
			for (int j = 0; j < myCurrGrid.getSize(); j++) {
				updateCell(myCurrGrid.getCell(i, j));
			}
		}
		
		myCurrGrid = myNewGrid;
	}

	@Override
	protected void updateCell(Cell curr) {
		// Does Nothing
	}

}

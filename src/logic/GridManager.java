package logic;

/**
 * @author George Bernard
 * 
 *	This class extends AbstractGridManager, but does nothing
 *	
 *	Never Instantiate this class, only added to reduce code 
 *	for getNextGridState method to be extended by managers following
 *	specific rules
 *
 */

class GridManager extends AbstractGridManager {
	
	@Override
	Grid getNextGridState(Grid grid) {
		
		// Make copies of input Grid
		myCurrGrid = new Grid( grid );
		myNewGrid  = new Grid( grid );
		
		// Update each cell
		for (int i = 0; i < grid.getSize(); i++) {
			for (int j = 0; j < grid.getSize(); j++) {
				updateCell(myCurrGrid.getCell(i, j));
			}
		}
		
		return myNewGrid;
	}

	@Override
	protected void updateCell(Cell curr) {
		// Does Nothing
	}

}

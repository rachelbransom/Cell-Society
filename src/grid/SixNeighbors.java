package grid;

import cell.Cell;

class SixNeighbors extends Neighbors{

	public SixNeighbors(Grid grid, int size) {
		super(grid, size);

	}

	@Override
	public void setFullNeighbors(int i, int j, Cell currCell) {
		currCell.getNeighbors().clear();

		// Top Left
		if(inBounds(i     , j - 1)) currCell.getNeighbors().add(myGrid.getCell(i     , j - 1));
		// Top Middle
		if(inBounds(i - 1 , j    )) currCell.getNeighbors().add(myGrid.getCell(i - 1 , j    ));
		// Top Right
		if(inBounds(i     , j + 1)) currCell.getNeighbors().add(myGrid.getCell(i     , j + 1));
		// Bottom Right
		if(inBounds(i + 1 , j + 1)) currCell.getNeighbors().add(myGrid.getCell(i + 1 , j + 1));
		// Bottom Middle
		if(inBounds(i + 1 , j    )) currCell.getNeighbors().add(myGrid.getCell(i + 1 , j    ));
		// Bottom Left
		if(inBounds(i + 1 , j - 1)) currCell.getNeighbors().add(myGrid.getCell(i + 1 , j - 1));
		
	}

	@Override
	public void setCardinalNeighbors(int i, int j, Cell currCell) {
		currCell.getNeighbors().clear();
		
		// Top Left
		if(inBounds(i     , j - 1)) currCell.getNeighbors().add(myGrid.getCell(i     , j - 1));
		// Top Middle
		if(inBounds(i - 1 , j    )) currCell.getNeighbors().add(myGrid.getCell(i - 1 , j    ));
		// Top Right
		if(inBounds(i     , j + 1)) currCell.getNeighbors().add(myGrid.getCell(i     , j + 1));
		// Bottom Middle
		if(inBounds(i + 1 , j    )) currCell.getNeighbors().add(myGrid.getCell(i + 1 , j    ));
	}
	
	
}
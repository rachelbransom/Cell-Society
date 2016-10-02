package grid;

import cell.Cell;

class RectangleNeighbors extends Neighbors{

	
	
	
	@Override
	public void setFullNeighbors(int i, int j, Cell currCell){
		
		currCell.getNeighbors().clear();

		// Top Left
		if(inBounds(i - 1 , j - 1)) currCell.getNeighbors().add(getCell(i - 1, j - 1));
		// Top Middle
		if(inBounds(i     , j - 1)) currCell.getNeighbors().add(getCell(i    , j - 1 ));
		// Top Right
		if(inBounds(i + 1 , j - 1)) currCell.getNeighbors().add(getCell(i + 1, j - 1));
		// Right Side
		if(inBounds(i + 1 , j    )) currCell.getNeighbors().add(getCell(i + 1, j    ));
		// Bottom Right
		if(inBounds(i + 1 , j + 1)) currCell.getNeighbors().add(getCell(i + 1, j + 1));
		// Bottom Middle
		if(inBounds(i     , j + 1)) currCell.getNeighbors().add(getCell(i    , j + 1));
		// Bottom Left
		if(inBounds(i - 1 , j + 1)) currCell.getNeighbors().add(getCell(i - 1, j + 1));
		// Left Side
		if(inBounds(i - 1 , j    )) currCell.getNeighbors().add(getCell(i - 1, j    ));

	}
	
	@Override
	public void setCardinalNeighbors(int i, int j, Cell currCell){
		currCell.getNeighbors().clear();

		// Top Middle
		if(inBounds(i     , j - 1)) currCell.getNeighbors().add(getCell(i    , j - 1 ));
		// Right Side
		if(inBounds(i + 1 , j    )) currCell.getNeighbors().add(getCell(i + 1, j    ));
		// Bottom Middle
		if(inBounds(i     , j + 1)) currCell.getNeighbors().add(getCell(i    , j + 1));
		// Left Side
		if(inBounds(i - 1 , j    )) currCell.getNeighbors().add(getCell(i - 1, j    ));
		
	}



	
}
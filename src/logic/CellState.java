package logic;

final class CellState {
	
	enum WaTorWorld{
		PREDATOR,
		PREY,
		EMPTY;
	}	

	enum GameOfLife{
		ALIVE,
		DEAD;
	}


	enum SpreadingFire{
		EMPTY,
		TREE,
		BURNING;
	}
	
	enum Segregation{
		POP_ONE,
		POP_TWO,
		EMPTY;
	}
	
}

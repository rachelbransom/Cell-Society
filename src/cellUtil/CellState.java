package cellUtil;

public final class CellState {
	
	Enum<WaTorWorld> WaTorStates;
	public enum WaTorWorld{
		PREDATOR,
		PREY,
		EMPTY;
	}	

	public enum GameOfLife{
		ALIVE,
		DEAD;
	}


	public enum SpreadingFire{
		EMPTY,
		TREE,
		BURNING;
	}
	
	public enum Segregation{
		POP_ONE,
		POP_TWO,
		EMPTY;
	}
	
}
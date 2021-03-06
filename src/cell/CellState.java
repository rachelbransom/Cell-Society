package cell;

public final class CellState {
	
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
	
	public enum SlimeMold{
		MOLD,
		EMPTY;
	}
	
	public enum SugarScape{
		AGENT,
		EMPTY;
	}
	
	public enum Langton{
		SHEATH,
		TURN,
		ADVANCE,
		MESSENGER,
		NOCOMMAND,
		EMPTY,
		MAKETURN,
		ENDLOOP;
		
	}
}

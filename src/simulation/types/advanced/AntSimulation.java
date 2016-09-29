package simulation.types.advanced;

import cellUtil.Cell;
import cellUtil.Grid;
import simulation.types.AbstractSimulation;

public class AntSimulation extends AbstractSimulation {

	private static final int MAX_ANTS_PER_CELL = 10;
	private static final int ANT_LIFETIME = 500;
	
	private static final double MAX_PHER = 1000.0;
	private static final double MIN_PHER = 0.0;
	
	public AntSimulation(Grid inputGrid) {
		super(inputGrid);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void updateCell(Cell curr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initColorMap() {
		// TODO Auto-generated method stub
		
	}

}

package simulation.types.advanced;
import cell.Cell;
import grid.Grid;
import simulation.types.hierarchy.AbstractSimulation;


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
		
		
	}
	
	protected void antForage(){
		// if ant has food item
			// return to nest
		//else
			// find food source
	}
	
	protected void returnAntToNest(){
		//if any located at food source
			// set ants orientation to the neighbor location with max pheremones
		// x = forward location with max pheremones
		// if X = null
			// x = neighbor location with max home pheremones
		// if x isn't null
			//drop food pheremones()
			//orientation = 
	}
}

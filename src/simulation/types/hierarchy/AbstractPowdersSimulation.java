package simulation.types.hierarchy;

import cell.CellState.SugarScape;
import cell.Floor;
import grid.Grid;
import simulation.types.SimulationType;

public abstract class AbstractPowdersSimulation extends AbstractSequentialSimulation {

	private int[] myAgentPowderMetabolism;
	
	private int[] myPowdersGrowBackRate;
	private int[] myMaximumPowdersCapacity;
	private int myPowderGrowBackInterval;
	private int myStepCounter;
	
	public AbstractPowdersSimulation(Grid inputGrid, int[] growBacks, int[] powdercaps, int[] metabs, int interval) {
		super(inputGrid);
		myPowdersGrowBackRate = growBacks;
		myMaximumPowdersCapacity = powdercaps;
		myAgentPowderMetabolism = metabs;
		myPowderGrowBackInterval = interval;		
		myStepCounter = 0;
		
		showCurrGrid().setNeighbors(SimulationType.SUGARSCAPE);
		initAgents();
	}
	
	@Override
	protected void updateGrid(){
		super.updateGrid();
		myStepCounter++;
		growBackPowders();
		showCurrGrid().setNeighbors(SimulationType.SUGARSCAPE);

	}
	
	protected void initAgents(){
		for (int x = 0; x < getSize(); x++) {
			for (int y = 0; y < getSize(); y++) {
				if(getCurrGrid().getCell(x, y).getActor().isState(SugarScape.AGENT)){
					for (int p = 0; p < myAgentPowderMetabolism.length; p++) {
						getCurrGrid().getCell(x, y).getActor().energies().add(p, 5);
					}
				}
			}
		}
	}
	
	protected void growBackPowders(){
		if((myStepCounter % myPowderGrowBackInterval) == 0)
			for(int p = 0; p < myPowdersGrowBackRate.length; p++)
				for (int x = 0; x < getSize(); x++) 
					for (int y = 0; y < getSize(); y++) {
						setPowder(x, y, p, getPowder(x, y, p) + myPowdersGrowBackRate[p] );
						if(getPowder(x,y,p) > myMaximumPowdersCapacity[p]){
							setPowder(x, y, p, (double) myMaximumPowdersCapacity[p]);
						}
					}

	}

	protected int getAgentMetabOf(int p){
		return myAgentPowderMetabolism[p];
	}
	
	protected void setPowder(int x, int y, int powdType, Double inp){
		try {
			getFloorAt(x,y).contents().set(powdType,inp);
		} catch (Exception e) {
			getFloorAt(x,y).contents().add(powdType,inp);
		}
		
	}
	
	protected Double getPowder(int x, int y, int powdType){
		return getFloorAt(x,y).contents().get(powdType);
	}
	
	protected Double takePowder(int x, int y, int p){
		Double powder = getPowder(x, y, p);
		setPowder(x, y, p, new Double(0.0));
		return powder;
	}
	
	protected Floor<Double> getFloorAt(int x, int y){
		return getCurrGrid().getCell(x, y).getFloor();
	}
	
}


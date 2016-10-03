package simulation.types.hierarchy;

import cell.Floor;
import grid.Grid;

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
	}
	
	@Override
	protected void updateGrid(){
		super.updateGrid();
		myStepCounter++;
		growBackPowders();
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

	protected Double getAgentMetabOf(int p){
		return (double) myAgentPowderMetabolism[p];
	}
	
	protected void setPowder(int x, int y, int powdType, Double inp){
		getFloorAt(x,y).contents().set(powdType,inp);
	}
	
	protected Double getPowder(int x, int y, int powdType){
		return getFloorAt(x,y).contents().get(powdType);
	}
	
	protected Double takePowder(int x, int y, int p){
		Double powder = getPowder(x, y, p);
		setPowder(x, y, p, 0.0);
		return powder;
	}
	
	protected Floor<Double> getFloorAt(int x, int y){
		return getCurrGrid().getCell(x, y).getFloor();
	}
	
}


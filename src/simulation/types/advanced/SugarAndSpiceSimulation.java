package simulation.types.advanced;

import cell.Actor;
import cell.Cell;
import cell.CellState.SugarScape;
import grid.Grid;
import simulation.types.hierarchy.AbstractPowdersSimulation;

public class SugarAndSpiceSimulation extends AbstractPowdersSimulation {

	public SugarAndSpiceSimulation(Grid inputGrid, int[] growBacks, int[] powdercaps, int[] metabs, int interval) {
		super(inputGrid, growBacks, powdercaps, metabs, interval);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void updateCell(Cell curr) {
		// TODO Auto-generated method stub
		
		// if Agent runs out of everything, it dies.
		
	}

	private void goToSiteWithMostWelfare(Cell c){
		Cell maxCampCell = new Cell();
		Double maxWelfare = 0.0;
		boolean foundMax = false;
		
		for(Cell eN: c.getNeighborsWithState(SugarScape.EMPTY)){
			Double currWelfare = getWelfare(eN.getFloor().contents().get(0),
											eN.getFloor().contents().get(1),
											c.getActor().energies().get(0),
											c.getActor().energies().get(1));
			if( maxWelfare < currWelfare ){
				foundMax = true;
				maxWelfare = currWelfare;
				maxCampCell = eN;
			}
		}
		
		if(foundMax){
			maxCampCell.setActor( c.getActor() );
			c.setActor( new Actor(SugarScape.EMPTY) );
		}
	}
	
	private double getWelfare(double seenSugar, double seenSpice, double mySugar, double mySpice){
		int welfareCoeff = 3;
		double sugarNeed = seenSugar / (Math.pow(mySugar, welfareCoeff));
		double spiceNeed = seenSpice / (Math.pow(mySpice, welfareCoeff));
		return sugarNeed + spiceNeed;
	}
	
}

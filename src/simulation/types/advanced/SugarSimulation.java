package simulation.types.advanced;

import cell.Actor;
import cell.Cell;
import cell.CellState.SugarScape;
import grid.Grid;
import simulation.types.hierarchy.AbstractPowdersSimulation;

public class SugarSimulation extends AbstractPowdersSimulation {

	
	// Agent Related Instance Variables
	private int myAgentSugarMetabolism;
	
	public SugarSimulation(Grid inputGrid, int growBack, int sugarCap, int metab, int interval) {
		super(inputGrid, new int[]{growBack}, new int[]{sugarCap}, new int[]{metab}, interval);
		myAgentSugarMetabolism = getAgentMetabOf(0);
	}

	@Override
	protected void updateCell(Cell curr) {
		if(curr.getActor().isState(SugarScape.AGENT)){
			
			// If Agent runs out of energy, kill it
			if(curr.getActor().getEnergy() < 0){
				curr.setActor(new Actor(SugarScape.EMPTY));
				return;
			}
			
			curr.getActor().setEnergy( curr.getActor().getEnergy() - myAgentSugarMetabolism);
			
			goToSiteWithMostSugar(curr);
		}
	}
	
	private void goToSiteWithMostSugar(Cell curr){
		
		Cell maxCampCell = new Cell();
		Double maxSugar = 0.0;
		boolean foundMax = false;
		
		for(Cell emptyNeighbor: curr.getNeighborsWithState(SugarScape.EMPTY)){
			Double currConct = emptyNeighbor.getFloor().contents().get(0);
			if( maxSugar < currConct ){
				foundMax = true;
				maxSugar = currConct;
				maxCampCell = emptyNeighbor;
			}
		}
		
		if(foundMax){
			maxCampCell.setActor( curr.getActor() );
			curr.setActor( new Actor( SugarScape.EMPTY) );
			maxCampCell.getActor().setEnergy( takeSugar( maxCampCell.getLocation().x, maxCampCell.getLocation().y).intValue() );
		}
	}
	
	private Double takeSugar(int x, int y){
		return takePowder(x, y, 0);
	}
}
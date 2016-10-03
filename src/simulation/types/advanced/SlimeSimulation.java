package simulation.types.advanced;


import cell.Actor;
import cell.Cell;
import cell.CellState.SlimeMold;
import grid.Grid;
import simulation.types.SimulationType;
import simulation.types.hierarchy.AbstractSequentialSimulation;

public class SlimeSimulation extends AbstractSequentialSimulation {

	private double mySniffThreshold;
	private double myPheroDepositRate;
	private double myEvaporationRate;
	
	public SlimeSimulation(Grid inputGrid, Double sniffThresh) {
		super(inputGrid);
		initBlankSlatePhero();
		getCurrGrid().setNeighbors(SimulationType.SLIME_MOLD);
		mySniffThreshold = sniffThresh;
		myPheroDepositRate = 0.1;
		myEvaporationRate = 0.6;
	}

	@Override
	protected void updateCell(Cell curr) {
		diffusePhero(curr);
		
		
		
		if(curr.getActor().isState(SlimeMold.MOLD)){
			depositPhero(curr);
			goToSiteWithMostPhero(curr);
		}		
		
		evaporatePhero(curr);
	}
	
	private void goToSiteWithMostPhero(Cell curr){
		
		Cell maxCampCell = new Cell();
		Double maxCamp = mySniffThreshold;
		boolean foundMax = false;
		
		for(Cell emptyNeighbor: curr.getNeighborsWithState(SlimeMold.EMPTY)){
			Double currConct = emptyNeighbor.getFloor().contents().get(0);
			if( maxCamp < currConct ){
				foundMax = true;
				maxCamp = currConct;
				maxCampCell = emptyNeighbor;
			}
		}
		
		if(foundMax){
			maxCampCell.setActor( curr.getActor() );
			curr.setActor( new Actor(SlimeMold.EMPTY) );
		}
	}
	
	private void initBlankSlatePhero(){
		for (int x = 0; x < getSize(); x++) {
			for (int y = 0; y < getSize(); y++) {
				getCurrGrid().getCell(x, y).getFloor().setContent( 0.0 );
			}
		}
	}
	
	private void setPhero( int x, int y, double phero ){
		showCurrGrid().getCell(x, y).getFloor().setContent( (Double) phero);
	}
	
	private double getPhero(int x, int y){
		return showCurrGrid().getCell(x, y).getFloor().getContent();
	}
	
	private void depositPhero( Cell curr ){
		curr.getFloor().setContent( curr.getFloor().getContent() + myPheroDepositRate);
		
		for(Cell neighbor: curr.getNeighbors()){
			neighbor.getFloor().setContent( neighbor.getFloor().contents().get(0) + myPheroDepositRate );
		}
	}
	
	private void diffusePhero( Cell curr ){		
		double currConct = curr.getFloor().getContent();
		double splitConct = currConct / curr.getNeighbors().size();
		
		curr.getFloor().setContent(splitConct);
		
		for (Cell neighbor : curr.getNeighbors()) {	
			neighbor.getFloor().setContent( neighbor.getFloor().getContent() + splitConct);
		}
	}
	
	private void evaporatePhero( Cell curr ){
		if(curr.getFloor().getContent() > 0)
		curr.getFloor().contents().set(0, curr.getFloor().getContent() - myEvaporationRate);
	}

}

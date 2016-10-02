package simulation.types.advanced;

import cell.Actor;
import cell.Cell;
import cell.CellState.SugarScape;
import cell.Floor;
import grid.Grid;
import simulation.types.hierarchy.AbstractSimulation;


public class SugarSimulation extends AbstractSimulation {
	
	// Agent Related Instance Variables
	private int myAgentSugarMetabolism;
	
	// Sugar related Instance Variables
	private int mySugarGrowBackRate;
	private int myMaximumSugarContent;
	private int mySugarGrowBackInterval;
	private int myStepCounter;
	
	public SugarSimulation(Grid inputGrid) {
		super(inputGrid);
		
		myAgentSugarMetabolism = 1;
		
		mySugarGrowBackRate = 2;
		myMaximumSugarContent = 10;
		mySugarGrowBackInterval = 2;
		myStepCounter = 0;
		
	}

	@Override
	protected void updateGrid(){
		super.updateGrid();
		myStepCounter++;
		growBackSugar();
	}
	
	@Override
	protected void updateCell(Cell curr) {
		if(curr.getActor().equals(SugarScape.AGENT)){
			
			// If Agent runs out of energy, kill it
			if(curr.getActor().getEnergy() < 0){
				curr.setActor(new Actor(SugarScape.EMPTY));
				return;
			}
			
			int x = curr.getLocation().x;
			int y = curr.getLocation().y;
			setSugar(x, y, getSugar(x, y) - myAgentSugarMetabolism);
			
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
	
	private Floor<Double> getFloorAt(int x, int y){
		return getCurrGrid().getCell(x, y).getFloor();
	}
	
	private Double getSugar(int x, int y){
		return getFloorAt(x, y).getContent();
	}
	
	private void setSugar(int x, int y, Double input){
		getFloorAt(x, y).setContent(input);
	}
	
	private Double takeSugar(int x, int y){
		Double sugar = getSugar(x, y);
		setSugar(x, y, 0.0);
		return sugar;
	}
	
	private void growBackSugar(){
		if((myStepCounter % mySugarGrowBackInterval) == 0){
			for (int x = 0; x < getSize(); x++) {
				for (int y = 0; y < getSize(); y++) {
					setSugar(x, y,  getSugar(x, y) + mySugarGrowBackRate );
					if(getSugar(x,y) > myMaximumSugarContent){
						setSugar(x, y, (double) myMaximumSugarContent);
					}
				}
			}
		}
	}

}

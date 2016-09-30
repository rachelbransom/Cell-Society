package simulation.types.basic;

import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

import cellUtil.BorderType;
import cellUtil.Actor;
import cellUtil.Cell;
import cellUtil.Grid;
import cellUtil.CellState.WaTorWorld;
import javafx.scene.paint.Color;
import simulation.types.SimulationType;
import simulation.types.hierarchy.AbstractSimulation;


public class WaTorWorldSimulation extends AbstractSimulation {

	private static final Enum OCEAN = WaTorWorld.EMPTY;
	private static final Enum SHARK = WaTorWorld.PREDATOR;
	private static final Enum FISH  = WaTorWorld.PREY;
	private static final int SEED = 1234;
	
	private Random myRand;
	private int mySharkReproductiveAge = 15;
	private int myFishReproductionAge = 1;
	private int EnergyThreshold = 2;
	
	public WaTorWorldSimulation(Grid inputGrid) {
		super(inputGrid);
		myRand = new Random(SEED);
		getCurrGrid().setNeighbors(SimulationType.WA_TOR_WORLD);
		initSharkAgeAndEnergy();
	}

	/*----------------- Overriden Methods -----------------------------*/

	@Override
	protected void updateGrid(){

		// Updates in sequential order, rather than in parallel
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				updateCell( getCurrGrid().getCell(i, j));
			}
		}
	}
	
	@Override
	protected void updateCell(Cell curr) {
		
		Actor actorReference = curr.getActor();
		
		// If ocean do nothing.
		if(curr.getActor().isState(OCEAN)) return;
		
		if(curr.getActor().isState(FISH)){			
			// Move Fish
			this.moveActorToRandomNeighborWithState(curr, OCEAN);
			
			// If fish can reproduce, leave behind a fish

			if(actorReference.getAge() % this.myFishReproductionAge == 0){
				curr.setActor( new Actor(FISH, 0, 0) );
			}
			
			actorReference.incrementAge();
		}
		
		if(curr.getActor().isState(SHARK)){
			
			actorReference.incrementAge();
			actorReference.decrementEnergy();
			
			// If shark runs out of energy, it dies
			if(actorReference.getEnergy() == 0){
				actorReference.changeState(OCEAN);
				return;
			}
			
			// If no fish Neighbors, move to random ocean block, lose one energy
			if(curr.numberNeighborsWithState(FISH) == 0){
				
				moveActorToRandomNeighborWithState(curr, OCEAN);				
			}
			
			// If any fishy neighbors
			if( curr.numberNeighborsWithState(FISH) > 0 ){
				
				moveActorToRandomNeighborWithState(curr, FISH);
				actorReference.incrementEnergy();
				actorReference.incrementEnergy();
					
			}
			
			// If able to reproduce leave behind shark
			if(actorReference.getAge() % mySharkReproductiveAge == 0){
				curr.setActor( new Actor(SHARK, this.EnergyThreshold, 0));
			}
		}
		
		
	}
	
	/*----------------- Private / Helper Methods -----------------------------*/
	
	private void moveActorToRandomNeighborWithState(Cell inputCell, Enum state){
		
		Collection<Cell> neighborsWithState = inputCell.getNeighborsWithState(state);
		Cell randomEmptyNeighbor;
		
		if(inputCell.numberNeighborsWithState(state) > 0) randomEmptyNeighbor = getRandomCell(neighborsWithState);
		else return;
		
		if( ! randomEmptyNeighbor.getActor().isState(state)) throw new RuntimeException("Improper Empty Checking");
		
		randomEmptyNeighbor.setActor( inputCell.getActor() ); // Move actor from inCell to empty cell
		inputCell.setActor( new Actor( OCEAN ) );
		
	};
	
	/**
	 * Adapted from - http://stackoverflow.com/questions/21092086/get-random-element-from-collection
	 * 
	 * @param from
	 * @return
	 */
	private Cell getRandomCell( Collection<Cell> from ){
		int i = myRand.nextInt(from.size());
		return (Cell) from.toArray()[i];
	}
	
	private void initSharkAgeAndEnergy(){
		
		for (int x = 0; x < getSize(); x++) 
			for (int y = 0; y < getSize(); y++) {
				
				Cell currCell = getCurrGrid().getCell(x, y);
				
				if( getCurrGrid().getCell(x, y).getActor().isState(SHARK) ){
					currCell.setActor( new Actor(SHARK, EnergyThreshold, 1) );
				}
				
				
			}
		
	}
	
}

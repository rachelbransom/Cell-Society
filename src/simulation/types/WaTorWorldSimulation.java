package simulation.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import cellUtil.Actor;
import cellUtil.Cell;
import cellUtil.Grid;
import cellUtil.CellState.WaTorWorld;
import javafx.scene.paint.Color;


public class WaTorWorldSimulation extends AbstractSimulation {

	private static final Enum OCEAN = WaTorWorld.EMPTY;
	private static final Enum SHARK = WaTorWorld.PREDATOR;
	private static final Enum FISH  = WaTorWorld.PREY;
	private static final int SEED = 1234;
	
	private Random myRand;
	private int ReproductionThreshold;
	private int EnergyThreshold;
	
	public WaTorWorldSimulation(Grid inputGrid) {
		super(inputGrid);
		myRand = new Random(SEED);
		myCurrGrid.setNeighbors(SimulationType.WA_TOR_WORLD);
	}

	/*----------------- Overriden Methods -----------------------------*/

	@Override
	protected void updateGrid(){

		// Updates in sequential order, rather than in parallel
		for (int i = 0; i < this.mySize; i++) {
			for (int j = 0; j < mySize; j++) {
				updateCell( myCurrGrid.getCell(i, j));
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
			if(actorReference.getAge() % this.ReproductionThreshold == 0){
				curr.getActor().changeState(FISH);
			}
			
			actorReference.incrementAge();
		}
		
		if(curr.getActor().isState(SHARK)){
			
			// If shark runs out of energy, it dies
			if(actorReference.getEnergy() == 0){
				actorReference.changeState(OCEAN);
				return;
			}
			
			// If no fish Neighbors, move to random ocean block, lose one energy
			if(curr.numberNeighborsWithState(FISH) == 0){
				
				moveActorToRandomNeighborWithState(curr, OCEAN);
				actorReference.decrementEnergy();
				
			}
			
			// If any fishy neighbors
			if( curr.numberNeighborsWithState(FISH) > 0 ){
				
				moveActorToRandomNeighborWithState(curr, FISH);
				actorReference.incrementEnergy();
					
			}
			
			if(actorReference.getAge() % ReproductionThreshold == 0){
				curr.setActor( new Actor(SHARK, this.EnergyThreshold, 0));
			}
				
			
			actorReference.incrementAge();
			
		}
		
		
	}
	
	@Override
	protected void initColorMap() {
		this.myColorMap = new HashMap<Enum, Color>();
		
		myColorMap.put(OCEAN, Color.BLUE);
		myColorMap.put(SHARK, Color.GRAY);
		myColorMap.put(FISH, Color.GOLD);
	
	}

	/*----------------- Private / Helper Methods -----------------------------*/
	
	private void moveActorToRandomNeighborWithState(Cell inputCell, Enum state){
		
		Collection<Cell> neighborsWithState = inputCell.getNeighborsWithState(state);
		Cell randomEmptyNeighbor = getRandomCell(neighborsWithState);
		
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
}

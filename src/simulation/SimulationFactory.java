package simulation;

import simulation.types.*;
import cellsociety_team23.XMLParser;

class SimulationFactory {
	String file;
	private SimulationType situation;
	private XMLParser parser;
	private AbstractSimulation out;
	
	public SimulationFactory(String chosenFile){
		file = chosenFile;
		parser = new XMLParser(file);
		situation = parser.getSimulationType();
		makeSimulation();
	}
	
	//TODO: Add Logic for building specific simulation types (i.e. game of life over 
	
	
	public void giveFilename(){
		
	}
	
	public AbstractSimulation makeSimulation(){
		switch (situation){
		case GAME_OF_LIFE:
			return out = new GameOfLifeSimulation(parser.getGrid());
		case SPREADING_FIRE:
			return out = new SpreadingFireSimulation(parser.getGrid(), parser.getGlobalConfiguration());
		case SEGREGATION:
			return out = new SegregationSimulation(parser.getGrid(), parser.getGlobalConfiguration());
		case WA_TOR_WORLD:
			return out = new WaTorWorldSimulation(parser.getGrid());
		}
		return out;
	}
}

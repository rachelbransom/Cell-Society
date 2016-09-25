package simulation;

import cellUtil.Grid;
import cellsociety_team23.XMLParser;
import simulation.types.*;

class SimulationFactory {
	String file;
	private SimulationType simulation;
	private XMLParser parser;
	private AbstractSimulation out;
	
	public SimulationFactory(String chosenFile){
		file = chosenFile;
		parser = new XMLParser(file);
		simulation = parser.getSimulationType();
	}
	
	//TODO: Add Logic for building specific simulation types (i.e. game of life over 
	
	
	public void giveFilename(){
		
	}
	
	public AbstractSimulation makeSimulation(){
		
		switch (simulation){
		case GAME_OF_LIFE:
			return out = new GameOfLifeSimulation(parser.getGrid());
		case SPREADING_FIRE:
			return out = new SpreadingFireSimulation(parser.getGrid(), parser.getGlobalConfiguration());
		case SEGREGATION:
			return out = new SegregationSimulation(parser.getGrid(), parser.getGlobalConfiguration());
		case WA_TOR_WORLD:
			return out = new WaTorWorldSimulation(parser.getGrid());		
		default:
			return out;
		}
	}
}

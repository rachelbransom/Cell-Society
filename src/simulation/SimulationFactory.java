package simulation;

import cellStateConfigurationType.ConfigurationType;

//@author Rachel Bransom

import cellsociety_team23.XMLParser;
import simulation.types.*;
import simulation.types.advanced.*;
import simulation.types.basic.*;
import simulation.types.hierarchy.AbstractSimulation;

class SimulationFactory {
	
	String file;
	private SimulationType simulation;
	private XMLParser parser;
	private AbstractSimulation out;
	
	public SimulationFactory(String chosenFile, String shape, ConfigurationType configType){
		file = chosenFile;
		parser = new XMLParser(file, shape, configType);
		simulation = parser.getSimulationType();
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
		case SLIME_MOLD:
			return out = new SlimeSimulation(parser.getGrid(), parser.getGlobalConfiguration());
		case SUGARSCAPE:
			return out = new SugarAndSpiceSimulation(parser.getGrid(), new int[]{4, 4}, 
																	new int[]{4, 4}, 
																	new int[]{1, 1}, 
																	2);
		default:
			return out;
		}
	}
	
	
	
}

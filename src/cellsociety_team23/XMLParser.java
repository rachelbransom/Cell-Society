package cellsociety_team23;

import java.io.File;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import cell.Actor;
import cell.Cell;
import cell.CellState;
import cellStateConfigurationType.ConfigurationType;
import grid.Grid;
import exceptions.InvalidCellState;
import exceptions.NoSimulation;

import simulation.types.SimulationType;
import simulation.visuals.Hexagon;
import simulation.visuals.Triangle;

public class XMLParser {
	Document document;
	String file;
	private SimulationType situation;
	private String title;
	private String author;
	private double globalConfig;
	private int gridDimensions;
	private int states;
	private int currCellState = 0;
	private Grid grid;
	private String shape;
	private ConfigurationType configurationType;
	private Random rand = new Random();

	public XMLParser(String chosenFileName, String shape, ConfigurationType configType) {
		file = chosenFileName;
		this.shape = shape;
		this.configurationType = configType;
		parseFile();

	}

	private void parseFile() {

		try {
			File XMLFile = new File("data/" + file + "/");
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			document = documentBuilder.parse(XMLFile);
			document.getDocumentElement().normalize();

			situation = (SimulationType) returnSimulationType(getTextByTag("situation"));

			try {
				testIfSimulation(situation);
			} catch (NoSimulation e) {
				e.callDialogBox();
				e.printStackTrace();
			}

			title = getTextByTag("title");
			author = getTextByTag("author");
			globalConfig = Double.parseDouble(getTextByTag("global_config"));
			gridDimensions = Integer.parseInt(getTextByTag("grid_dimensions"));
			states = Integer.parseInt(getTextByTag("states"));
			grid = new Grid(gridDimensions, shape);
			for (int i = 0; i < gridDimensions; i++) {
				for (int j = 0; j < gridDimensions; j++) {
					Cell currCell = new Cell(i, j);
					
					if (this.configurationType.equals(ConfigurationType.RANDOM)){
						currCellState = setCellStateByRandom(states);
					}
					else{
						currCellState = setCellStateByXML(i, j, situation);
					}
					
					
					if (situation == SimulationType.SUGARSCAPE) {
						int sugarFloor = Integer.parseInt(getTextByTag("cellFloorSugar" + i + "." + j));
						int spiceFloor = Integer.parseInt(getTextByTag("cellFloorSpice" + i + "." + j));

						currCell.getFloor().contents().set(0, (double) sugarFloor);
						currCell.getFloor().contents().add(1, (double) spiceFloor);
					}

					Actor currAct = new Actor(returnCellState(situation, currCellState));
					currCell.setActor(currAct);
					grid.setCell(i, j, currCell);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int setCellStateByXML(int i, int j, SimulationType situation){
		int currCellState = Integer.parseInt(getTextByTag("cell" + i + "." + j));

		try {
			testIfValidCellState(currCellState, situation);
		} catch (InvalidCellState e) {
			e.callDialogBox();
			e.printStackTrace();
		}
		
		return currCellState;
	}
	
	private int setCellStateByRandom(int states){
		return rand.nextInt(states);
	}

	public SimulationType getSimulationType() {
		return situation;
	}

	public double getGlobalConfiguration() {
		return globalConfig;
	}

	public int getNumberOfStates() {
		return states;
	}

	public int getGridDimensions() {
		return gridDimensions;
	}

	public Grid getGrid() {
		return grid;
	}

	private String getTextByTag(String tag) {
		return document.getElementsByTagName(tag).item(0).getTextContent();
	}

	private Enum<SimulationType> returnSimulationType(String situation) {
		switch (situation) {
		case "life":
			return SimulationType.GAME_OF_LIFE;
		case "fire":
			return SimulationType.SPREADING_FIRE;
		case "segregation":
			return SimulationType.SEGREGATION;
		case "wator":
			return SimulationType.WA_TOR_WORLD;
		case "slime":
			return SimulationType.SLIME_MOLD;
		case "sugar":
			return SimulationType.SUGARSCAPE;
		default:
			return null;
		}
	}

	private Enum returnCellState(SimulationType simType, int state) {
		switch (simType) {
		case GAME_OF_LIFE:
			switch (state) {
			case 0:
				return CellState.GameOfLife.DEAD;
			case 1:
				return CellState.GameOfLife.ALIVE;
			}
			break;
		case SPREADING_FIRE:
			switch (state) {
			case 0:
				return CellState.SpreadingFire.EMPTY;
			case 1:
				return CellState.SpreadingFire.BURNING;
			case 2:
				return CellState.SpreadingFire.TREE;
			}
			break;
		case SEGREGATION:
			switch (state) {
			case 0:
				return CellState.Segregation.EMPTY;
			case 1:
				return CellState.Segregation.POP_ONE;
			case 2:
				return CellState.Segregation.POP_TWO;
			}
			break;
		case WA_TOR_WORLD:
			switch (state) {
			case 0:
				return CellState.WaTorWorld.EMPTY;
			case 1:
				return CellState.WaTorWorld.PREDATOR;
			case 2:
				return CellState.WaTorWorld.PREY;
			}
			break;
		}
		return null;
	}

	/*----------------- Exceptions -----------------------------*/

	private static void testIfSimulation(SimulationType simulationType) throws NoSimulation {
		if (simulationType == null)
			throw new NoSimulation();
	}

	private static void testIfValidCellState(int state, SimulationType simulationType) throws InvalidCellState {
		if (((0 > state || 1 < state) && (simulationType.equals(SimulationType.GAME_OF_LIFE)))
				|| (0 > state || 2 < state)) {
			throw new InvalidCellState();
		}
	}
}

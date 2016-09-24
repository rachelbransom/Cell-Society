package cellsociety_team23;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import cellUtil.Actor;
import cellUtil.Cell;
import cellUtil.CellState;
import cellUtil.Grid;
import simulation.types.SimulationType;

public class XMLParser {
	Document document;
	String file;
	private SimulationType situation;
	private String title;
	private String author;
	private double globalConfig;
	private int gridDimensions;
	private int states;
	private Grid grid;

	public XMLParser(String chosenFileName) {
		file = chosenFileName;
		parseFile();
	}

	public void parseFile() {

		try {
			File XMLFile = new File("data/" + file + "/");
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			document = documentBuilder.parse(XMLFile);
			document.getDocumentElement().normalize();

			situation = (SimulationType) returnSimulationType(getTextByTag("situation"));
			title = getTextByTag("title");
			author = getTextByTag("author");
			globalConfig = Double.parseDouble(getTextByTag("global_config"));
			gridDimensions = Integer.parseInt(getTextByTag("grid_dimensions"));
			states = Integer.parseInt(getTextByTag("states"));

			for (int i = 0; i < gridDimensions ; i++) {
				for (int j = 0; j < gridDimensions ; j++) {
					grid = new Grid(gridDimensions);
					Cell cell = new Cell(i, j);
					cell.getActor().changeState(
							returnCellState(situation, Integer.parseInt(getTextByTag("cell" + i + "." + j ))));
					grid.setCell(i, j, cell);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SimulationType getSimulationType() {
		return situation;
	}

	public double getGlobalConfiguration() {
		return globalConfig;
	}

	public int getGridDimensions() {
		return gridDimensions;
	}

	public Grid getGrid() {
		return grid;
	}

	public String getTextByTag(String tag) {
		return document.getElementsByTagName(tag).item(0).getTextContent();
		//System.out.println(document.getElementsByTagName(tag).item(0).getTextContent());
	}

	public Enum<SimulationType> returnSimulationType(String situation) {
		switch (situation) {
		case "life":
			return SimulationType.GAME_OF_LIFE;
		case "fire":
			return SimulationType.SPREADING_FIRE;
		case "segregation":
			return SimulationType.SEGREGATION;
		case "wator":
			return SimulationType.WA_TOR_WORLD;
		}
		return null;
	}

	public Enum returnCellState(SimulationType simType, int state) {
		switch (simType) {
		case GAME_OF_LIFE:
			switch (state) {
			case 1:
				return CellState.GameOfLife.DEAD;
			case 2:
				return CellState.GameOfLife.ALIVE;
			}
		case SPREADING_FIRE:
			switch (state) {
			case 0:
				return CellState.SpreadingFire.EMPTY;
			case 1:
				return CellState.SpreadingFire.BURNING;
			case 2:
				return CellState.SpreadingFire.TREE;
			}
		case SEGREGATION:
			switch (state) {
			case 0:
				return CellState.Segregation.EMPTY;
			case 1:
				return CellState.Segregation.POP_ONE;
			case 2:
				return CellState.Segregation.POP_TWO;
			}
		case WA_TOR_WORLD:
			switch (state) {
			case 0:
				return CellState.WaTorWorld.EMPTY;
			case 1:
				return CellState.WaTorWorld.PREDATOR;
			case 2:
				return CellState.WaTorWorld.PREY;
			}
		}
		return null;
	}
}
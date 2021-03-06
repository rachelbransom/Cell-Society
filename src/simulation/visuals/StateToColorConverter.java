package simulation.visuals;

import java.util.HashMap;
import java.util.Map;

import cell.CellState.*;

import grid.Grid;
import javafx.scene.paint.Color;
import simulation.types.basic.*;
import simulation.types.advanced.*;
import simulation.types.hierarchy.AbstractSimulation;
import simulationColorScheme.ColorScheme;

public class StateToColorConverter {

	private	Map<Enum, Color> myStateToColorMap;
	private AbstractSimulation mySimulation;
	private Color[] myFloorColors;
	
	public StateToColorConverter(AbstractSimulation sim) {
		myStateToColorMap = new HashMap<Enum, Color>();
		mySimulation = sim;
		chooseColorScheme(mySimulation);
	}
	
	public Color[][] showCurrColorGrid(ColorScheme color){
		applyColorChoice(color);
		return convertStateGridToColorGrid(mySimulation.showCurrGrid());
	}
	
	public Color[][] showNextColorGrid(ColorScheme color){
		applyColorChoice(color);
		return convertStateGridToColorGrid(mySimulation.showNextGrid());
	}
	
	private Color[][] convertStateGridToColorGrid(Grid stateGrid){

		int size = mySimulation.showCurrGrid().getSize();
		Color[][] colorGrid = new Color[size][size];

		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				Enum currState = stateGrid.getCell(x, y).getActor().getState();
				colorGrid[x][y] = myStateToColorMap.get(currState);
			}
		}
		

		return colorGrid;
	}
	
	private void chooseColorScheme(AbstractSimulation sim){
		
		Class<? extends AbstractSimulation> simClass = sim.getClass();
		
		if( simClass.equals(GameOfLifeSimulation.class) ) initGameOfLifeColorScheme();
		
		if( simClass.equals(SegregationSimulation.class) ) initSegregationColorScheme();
		
		if( simClass.equals(SpreadingFireSimulation.class) ) initSpreadingFireColorScheme();
		
		if( simClass.equals(WaTorWorldSimulation.class) ) initWaTorColorScheme();
		
		if( simClass.equals(AntSimulation.class)) initForagingAntsColorScheme();
		
		if( simClass.equals(LangtonSimulation.class) ) initLangtonColorScheme();
		
		if( simClass.equals(SlimeSimulation.class) ) initSlimeMoldColorScheme();
		
		if( simClass.equals(SugarSimulation.class) ) initSugarScapeColorScheme();
		
		if( simClass.equals(SugarAndSpiceSimulation.class)) initSugarSpiceColorScheme();
	}

	/****************** Initialize Maps *************************/

	private void initGameOfLifeColorScheme(){
		myStateToColorMap.put(GameOfLife.DEAD, Color.WHITE);
		myStateToColorMap.put(GameOfLife.ALIVE, Color.BLACK);
	}

	private void initSegregationColorScheme(){
		myStateToColorMap.put(Segregation.EMPTY, Color.WHITE);
		myStateToColorMap.put(Segregation.POP_ONE, Color.RED);
		myStateToColorMap.put(Segregation.POP_TWO, Color.BLUE);
	}
	
	private void initSpreadingFireColorScheme(){
		myStateToColorMap.put(SpreadingFire.EMPTY, Color.BURLYWOOD);
		myStateToColorMap.put(SpreadingFire.TREE, Color.DARKGREEN);
		myStateToColorMap.put(SpreadingFire.BURNING, Color.ORANGERED);
	}

	private void initWaTorColorScheme(){
		myStateToColorMap.put(WaTorWorld.EMPTY, Color.LIGHTSEAGREEN);
		myStateToColorMap.put(WaTorWorld.PREDATOR, Color.DARKGREY);
		myStateToColorMap.put(WaTorWorld.PREY, Color.GOLDENROD);
	}

	private void initLangtonColorScheme(){
		myStateToColorMap.put(Langton.SHEATH, Color.RED);
		myStateToColorMap.put(Langton.TURN, Color.FORESTGREEN);
		myStateToColorMap.put(Langton.ADVANCE, Color.YELLOW);
		myStateToColorMap.put(Langton.MESSENGER, Color.DEEPPINK);
		myStateToColorMap.put(Langton.EMPTY, Color.BLACK);
		myStateToColorMap.put(Langton.NOCOMMAND, Color.WHITE);
		myStateToColorMap.put(Langton.MAKETURN, Color.AQUAMARINE);
		myStateToColorMap.put(Langton.ENDLOOP, Color.BLUE);
	}
	
	private void initSlimeMoldColorScheme(){
		myStateToColorMap.put(SlimeMold.MOLD, Color.GREENYELLOW);
		myStateToColorMap.put(SlimeMold.EMPTY, Color.GHOSTWHITE);
		myFloorColors = new Color[]{Color.DARKSEAGREEN};
	}

	private void initSugarScapeColorScheme(){
		myStateToColorMap.put(SugarScape.AGENT, Color.MAROON);
		myStateToColorMap.put(SugarScape.EMPTY, Color.WHITE);
		myFloorColors = new Color[]{Color.BISQUE};
	}
	
	private void initSugarSpiceColorScheme() {
		initSugarScapeColorScheme();
		myFloorColors = new Color[]{myFloorColors[0], Color.CORNFLOWERBLUE};
	}
	
	private void initForagingAntsColorScheme(){}
	
	
	private void applyColorChoice(ColorScheme color){
		for (Enum state : myStateToColorMap.keySet()){
			switch (color) {
			case NORMAL:
				// nothing
				break;
			case BRIGHTEN:
				myStateToColorMap.put(state, myStateToColorMap.get(state).brighter());
				break;
			case DARKEN:
				myStateToColorMap.put(state, myStateToColorMap.get(state).darker());
				break;
			case SATURATE:
				myStateToColorMap.put(state, myStateToColorMap.get(state).saturate());
				break;
			case GRAYSCALE:
				myStateToColorMap.put(state, myStateToColorMap.get(state).grayscale());
				break;
			case INVERT:
				myStateToColorMap.put(state, myStateToColorMap.get(state).invert());
				break;
			}
			
		}
	}
	
}

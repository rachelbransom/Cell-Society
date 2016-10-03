package simulation;

import java.util.HashMap;

import graph.PopulationGraph;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.paint.Color;
import simulation.types.hierarchy.AbstractSimulation;
import simulation.visuals.SimulationVisualizer;
import simulation.visuals.StateToColorConverter;
import simulationColorChoices.ColorChoice;

//@authour: Rachel Bransom
//@author: Diane Hadley

public class SimulationController {

	private AbstractSimulation mySimulation;
	private SimulationVisualizer myVisualizer;
	private String myShape;
	private HashMap<Color, Integer> populationMap;
	private PopulationGraph myPopulationGraph;
	private ColorChoice userColorChoice;
	
	public void initializeSimulation(String filename, String shape){		  
		myShape = shape;
		SimulationFactory factory = new SimulationFactory(filename, myShape);
		mySimulation = factory.makeSimulation();
		
	}
	
	public Group returnCurrVisualGrid(Boolean withGridOutlines, ColorChoice colorChoice){
		this.userColorChoice = colorChoice;
		Color[][] colorGrid = new StateToColorConverter(mySimulation).showCurrColorGrid(colorChoice);
		myVisualizer = new SimulationVisualizer(colorGrid.length, myShape, withGridOutlines);
		Group gridRoot = makeGridRoot(colorGrid);
		myPopulationGraph = new PopulationGraph(myVisualizer.getPopulationMap());
		return gridRoot;
	}
	
	public Group returnNextVisualGrid(){
		Color[][] colorGrid = new StateToColorConverter(mySimulation).showNextColorGrid(userColorChoice);
		Group gridRoot = makeGridRoot(colorGrid);
		myPopulationGraph.update(myVisualizer.getPopulationMap());
		return gridRoot;
	}	
	
	private Group makeGridRoot( Color[][] colorGrid ){
		Group gridRoot = myVisualizer.returnVisualGrid(colorGrid);
		
		return gridRoot;
	}
	
	public Group getPopulationChart(){
		Group graphRoot = new Group();
		graphRoot.getChildren().add(myPopulationGraph.getMyLineChart());
		return graphRoot;
	}
	
	public void setMyLineChartLayout(int x, int y){
		myPopulationGraph.setMyLineChartLayout(x, y);
	}
	

}
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

//@authour: Rachel Bransom
//@author: Diane Hadley

public class SimulationController {

	private AbstractSimulation mySimulation;
	private SimulationVisualizer myVisualizer;
	private HashMap<Color, Integer> populationMap;
	private PopulationGraph myPopulationGraph;
	
	public void initializeSimulation(String filename, String shape){		  
		SimulationFactory factory = new SimulationFactory(filename);
		mySimulation = factory.makeSimulation();
	}

	
//	public LineChart<Number,Number> getSimulationChart(){
//		return mySimulation.getMyChart();
//	}
	
//	public void setSimulationChartLayout(int x, int y){
//		mySimulation.setMyChartLayout(x, y);
//	}
	
	public Group returnCurrVisualGrid(Boolean withGridOutlines){
		Color[][] colorGrid = new StateToColorConverter(mySimulation).showCurrColorGrid();
		myVisualizer = new SimulationVisualizer(colorGrid.length, withGridOutlines);
		Group gridRoot = makeGridRoot(colorGrid);
		myPopulationGraph = new PopulationGraph(myVisualizer.getPopulationMap());
		return gridRoot;
	}
	
	public Group returnNextVisualGrid(){
		Color[][] colorGrid = new StateToColorConverter(mySimulation).showNextColorGrid();
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
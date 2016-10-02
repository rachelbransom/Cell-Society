package graph;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;

public class PopulationGraph {
	private ArrayList<XYChart.Series<Number,Number>> series = new ArrayList<XYChart.Series<Number,Number>>();
	private HashMap<Color,Integer> myInitialPopulationMap;
	private HashMap<Color,Integer> myMap;
	private LineChart myLineChart;
	private NumberAxis xAxis, yAxis;
	private final int X_AXIS_MAX = 200, X_AXIS_INTERVAL = 5;
	private int counter;
	
	public PopulationGraph(HashMap<Color,Integer> initialMap){
		this.myInitialPopulationMap = initialMap;
		init();
	}
	
	public void update(HashMap<Color,Integer> map){
		counter++;
		int i = 0;
		
//		for (Color color: myMap.keySet()){
//			myMap.put(color, 0);
//		}
		
		for (Color color: myInitialPopulationMap.keySet()){
//			System.out.println("Color: " + color + ", Number: " + map.get(color));
//			if (myMap.containsKey(color)){
//				//System.out.println(map.get(color));
//				myMap.put(color, map.get(color));
//			}
//			else {
//				myMap.put(color, 0);
//			}
			series.get(i).getData().add(new XYChart.Data(counter, map.get(color)));
			i++;
		}
		
	}
	
	private void init(){
		counter = 0;
		myMap = myInitialPopulationMap;
		
		for (Color color: myInitialPopulationMap.keySet()){
			XYChart.Series<Number,Number> chart = new XYChart.Series<Number,Number>();
			chart.getData().add(new XYChart.Data(counter, myInitialPopulationMap.get(color)));
			
			System.out.println(color.toString().substring(2, color.toString().length()-2));
			System.out.println(Color.RED.toString());
			//chart.nodeProperty().get().setStyle("-fx-stroke: #FFFFFF;");
			//chart.nodeProperty().get().setStyle();
			series.add(chart);
		}
		
		xAxis = new NumberAxis(0, X_AXIS_MAX, X_AXIS_INTERVAL);
		yAxis = new NumberAxis();
		
		xAxis.setForceZeroInRange(false);
		//xAxis.setAutoRanging(false);
		xAxis.setTickMarkVisible(false);
		xAxis.setTickLabelsVisible(false);
		
		myLineChart = new LineChart<Number,Number>(xAxis, yAxis);
		myLineChart.setPrefWidth(485);
		myLineChart.setPrefHeight(220);
		myLineChart.getData().addAll(series);
		
	}
	
	public LineChart getMyLineChart(){
		return myLineChart;
	}
	
	public void setMyLineChartLayout(int x, int y){
		myLineChart.setLayoutX(x);
		myLineChart.setLayoutY(y);
	}
	
}

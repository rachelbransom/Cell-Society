package graph;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import jdk.nashorn.internal.ir.Node;

public class PopulationGraph {
	private ArrayList<XYChart.Series<Number,Number>> series = new ArrayList<XYChart.Series<Number,Number>>();
	private HashMap<Color,Integer> myInitialPopulationMap;
	private HashMap<Color,Integer> myMap;
	private LineChart myLineChart;
	private NumberAxis xAxis, yAxis;
	private final int X_AXIS_MAX = 20, X_AXIS_INTERVAL = 1;
	private int counter;
	private static final String CSS_FILE_NAME = "resources/ChartStyling.css";
	
	public PopulationGraph(HashMap<Color,Integer> initialMap){
		this.myInitialPopulationMap = initialMap;
		init();
	}
	
	public void update(HashMap<Color,Integer> map){
		counter++;
		int i = 0;
		
		
		for (Color color: myInitialPopulationMap.keySet()){
//			System.out.println("Color: " + color + ", Number: " + map.get(color));
			if (map.containsKey(color)){
				//System.out.println(map.get(color));
				myMap.put(color, map.get(color));
			}
			else {
				myMap.put(color, 0);
			}
			series.get(i).getData().add(new XYChart.Data(counter, myMap.get(color)));
			i++;
		}
		
	}
	
	private void init(){
		counter = 0;
		myMap = myInitialPopulationMap;
		
		xAxis = new NumberAxis(0, X_AXIS_MAX, X_AXIS_INTERVAL);
		yAxis = new NumberAxis();
		
		xAxis.setForceZeroInRange(false);
		xAxis.setAutoRanging(false);
		xAxis.setTickMarkVisible(false);
		xAxis.setTickLabelsVisible(false);
		
		myLineChart = new LineChart<Number,Number>(xAxis, yAxis);
		myLineChart.getStylesheets().add(CSS_FILE_NAME);
		myLineChart.setPrefWidth(485);
		myLineChart.setPrefHeight(195);
		myLineChart.setLegendVisible(false);
		
		for (Color color: myInitialPopulationMap.keySet()){
			XYChart.Series<Number,Number> chart = new XYChart.Series<Number,Number>();
			chart.getData().add(new XYChart.Data(counter, myInitialPopulationMap.get(color)));
			
			//System.out.println("N" + color.toString());
			//chart.getNode().getStyleClass().add("line");
			//chart.getNode().getStyleClass().add("N" + color.toString());
			//javafx.scene.Node line = chart.getNode().lookup(".chart-series-area-line");
			//line.setStyle("-fx-stroke: ");
			
//			String rgb = String.format("%d, %d, %d",
//			        (int) (color.getRed() * 255),
//			        (int) (color.getGreen() * 255),
//			        (int) (color.getBlue() * 255));

			//line.setStyle("-fx-stroke: rgba(" + rgb + ", 1.0);");
			//System.out.println(Color.RED.toString());
			//chart.nodeProperty().get().setStyle("-fx-stroke: rgba(" + rgb + ", 1.0);");
			//chart.nodeProperty().get().setStyle();
			series.add(chart);
		}
		
		
		myLineChart.getData().addAll(series);
		//cellSocietyText.getStyleClass().add("title");
	}
	
	public LineChart getMyLineChart(){
		return myLineChart;
	}
	
	public void setMyLineChartLayout(int x, int y){
		myLineChart.setLayoutX(x);
		myLineChart.setLayoutY(y);
	}
	
}

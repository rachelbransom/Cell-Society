package cellsociety_team23;

import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import simulation.SimulationController;

/**
 * This class manages the visualization window
 * 
 * @author Rachel Bransom
 * @author Diane Hadley
 *
 */

public class UX extends UXConfiguration{
	

	private Scene scene;
	private Group root = new Group();
	private Group gridRoot = new Group();
	private Group graphRoot = new Group();
	private Timeline animation;
	
	private SimulationController simulationControl;	
	

	public static final int FRAMES_PER_SECOND = 1;
	private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private static final String RESOURCE_FILE_NAME = "resources/DisplayedText";
	private static final String CSS_FILE_NAME = "resources/UXStyling.css";
	
	public static int GRID_START_X = 250;
	public static int GRID_START_Y = 300;
	public static int GRID_SIZE = 430;

	public String getTitle() {
		return myResources.getString("Title");
	}
	
	public UX() {
		myResources = ResourceBundle.getBundle(RESOURCE_FILE_NAME);
	}

	public Scene init() {
		scene = new Scene(root, Main.XSIZE, Main.YSIZE, Color.BLACK);
		scene.getStylesheets().add(CSS_FILE_NAME);
		
		buttonInit();
		buttonActionInit();
		
		root.getChildren().addAll(getButtons());
		root.getChildren().addAll(
				sliderInit(),
				xmlComboBoxInit(),
				shapeComboBoxInit(),
				displayInstructions(),
				displayTitle(),
				displaySliderText());
		
		root.getChildren().add(gridRoot);
		
		return scene;
	}
	
	private void buttonActionInit(){
		play.setOnAction((event) -> {
			playSimulation();
		});
		stop.setOnAction((event) -> {
			stopSimulation();
		});
		step.setOnAction((event) -> {
			step();
		});
		reset.setOnAction((event) -> {
			resetSimulation();
		});
	}

	private void playSimulation() {
		//root.getChildren().add(this.simulationControl.getSimulationChart());
		double speedMultiplier = slider.getValue();
		KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY / speedMultiplier), e -> step());
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	private void stopSimulation() {
		if (animation != null) {
			animation.stop();
		}
	}

	private void step() {
		if (simulationControl != null) {
			advanceGridRoot();
			
		}
		checkSpeed();
	}

	private void resetSimulation() {
		String file = getFile(getXMLComboBoxValue());
		stopSimulation();
		if (!file.equals("NONE CHOSEN")) {
			simulationControl = new SimulationController();
			simulationControl.initializeSimulation(file);
			resetGridRoot();
		}
	}

	private void resetGridRoot() {
		root.getChildren().remove(gridRoot);
		gridRoot = simulationControl.returnCurrVisualGrid();
		root.getChildren().add(gridRoot);
	}

	private void advanceGridRoot() {
		root.getChildren().remove(gridRoot);
		gridRoot = simulationControl.returnNextVisualGrid();
		root.getChildren().add(gridRoot);
	}
	
	private void checkSpeed() {
		if (slider.isValueChanging() == true) {
			stopSimulation();
			playSimulation();
		}
	}
	
	
//	private String getShape(String chosenShape){
//		switch(chosenShape) {
//		case("SQUARE"):
//			
//		
//		case("TRIANGLE"):
//			
//		}	
//	}
	
}
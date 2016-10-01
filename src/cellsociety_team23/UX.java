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

public class UX {
	
	private final int TITLE_X = 10, TITLE_Y = 100, 
			CONTROLS_START_X = 10, CONTROLS_START_Y = 150, CONTROLS_SPACING = 45,
			INSTRUCTIONSX = 275, INSTRUCTIONSY = 350,
			SLIDER_TEXT_X = CONTROLS_START_X + 50, 
			SLIDER_MIN = 1, SLIDER_MAX = 10, SLIDER_DEFAULT = 3;

	private Scene scene;
	private Group root = new Group();
	private Group gridRoot = new Group();
	private Group graphRoot = new Group();
	private Timeline animation;
	private Button play, stop, step, reset;
	private Slider slider;
	private ComboBox<String> xmlComboBox;
	private ComboBox<String> shapeComboBox;
	private Text cellSocietyText, instructionsText, sliderText;
	private SimulationController simulationControl;	
	private ResourceBundle myResources;

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
		sliderInit();
		xmlComboBoxInit();
		shapeComboBoxInit();
		displayInstructions();
		displayTitle();
		displaySliderText();
		root.getChildren().add(gridRoot);
		
		return scene;
	}

	
	private void buttonInit() {
		play = new Button(myResources.getString("PlayButton"));
		stop = new Button(myResources.getString("StopButton"));
		step = new Button(myResources.getString("StepButton"));
		reset = new Button(myResources.getString("ResetButton"));
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
		root.getChildren().addAll(setControlLayout(play, 0), setControlLayout(stop, CONTROLS_SPACING),
				setControlLayout(step, CONTROLS_SPACING * 2), setControlLayout(reset, CONTROLS_SPACING * 3));
	}

	private void playSimulation() {
		this.simulationControl.setSimulationChartLayout(GRID_START_X-65, GRID_START_Y-175);
		root.getChildren().add(this.simulationControl.getSimulationChart());
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
	
	private String getFile(String chosenFileName) {
		switch (chosenFileName) {
		case ("SEGREGATION"):
			return "Segregation.xml";
		case ("PREDATOR-PREY"):
			return "Wator.xml";
		case ("FIRE"):
			return "Fire.xml";
		case ("GAME OF LIFE"):
			return "Life.xml";
		case ("CHOOSE XML FILE"):
			return "NONE CHOSEN";
		case ("NO SIMULATION TYPE"):
			return ("NoSimulationType.xml");
		case ("INVALID CELL STATE"):
			return ("InvalidCellState.xml");
		}
		return null;
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
	
	

	/*----------------- Private / Helper Methods -----------------------------*/

	private void xmlComboBoxInit() {
		ObservableList<String> xmlOptions = FXCollections.observableArrayList(myResources.getString("Segregation"), 
				myResources.getString("PredatorPrey"),myResources.getString("Fire"),myResources.getString("GameOfLife"),
				myResources.getString("NoSimulationType"), myResources.getString("InvalidCellState"));
		xmlComboBox = new ComboBox<String>(xmlOptions);
		xmlComboBox.setValue(myResources.getString("XMLComboBoxText"));
		root.getChildren().add(setControlLayout(xmlComboBox, CONTROLS_SPACING * 5));
	}

	private void shapeComboBoxInit() {
		ObservableList<String> shapeOptions = FXCollections.observableArrayList(myResources.getString("Square"), 
				myResources.getString("Triangle"));
		shapeComboBox = new ComboBox<String>(shapeOptions);
		shapeComboBox.setValue(myResources.getString("ShapeComboBoxText"));
		root.getChildren().add(setControlLayout(shapeComboBox, CONTROLS_SPACING * 6));
	}
	
	private void sliderInit() {
		slider = new Slider(SLIDER_MIN, SLIDER_MAX, SLIDER_DEFAULT);
		slider.setMajorTickUnit(1f);
		root.getChildren().add(setControlLayout(slider, CONTROLS_SPACING * 4));
	}

	private void displayInstructions() {
		instructionsText = new Text(INSTRUCTIONSX, INSTRUCTIONSY, myResources.getString("Instructions"));
		instructionsText.getStyleClass().add("instructions");
		root.getChildren().add(instructionsText);
	}

	private String getXMLComboBoxValue() {
		return xmlComboBox.getSelectionModel().getSelectedItem();
	}
	
	private String getShapeComboBoxValue() {
		return shapeComboBox.getSelectionModel().getSelectedItem();
	}

	private void displaySliderText() {
		sliderText = new Text(SLIDER_TEXT_X, CONTROLS_START_Y + CONTROLS_SPACING*4, myResources.getString("SliderText"));
		sliderText.getStyleClass().add("slider");
		root.getChildren().add(sliderText);
	}

	private void displayTitle() {
		cellSocietyText = new Text(TITLE_X, TITLE_Y, myResources.getString("DisplayTitle"));
		cellSocietyText.getStyleClass().add("title");
		root.getChildren().add(cellSocietyText);
	}

	private Control setControlLayout(Control control, int layoutY) {
		control.setLayoutX(CONTROLS_START_X);
		control.setLayoutY(CONTROLS_START_Y + layoutY);
		control.setFocusTraversable(false);
		control.getStyleClass().add("control");
		return control;
	}
}
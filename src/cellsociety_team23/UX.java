package cellsociety_team23;

import java.util.ResourceBundle;
import exceptions.NoShapeChosen;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import simulation.SimulationController;
import simulationColorScheme.ColorScheme;

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
			SPEED_SLIDER_MIN = 1, SPEED_SLIDER_MAX = 10, SPEED_SLIDER_DEFAULT = 3;
			
	private final double PROBABILITY_SLIDER_MIN = 0.0, PROBAILITY_SLIDER_MAX = 1.0 , PROBABILITY_SLIDER_DEFAULT = 0.3;

	private Scene scene;
	private Group root = new Group();
	private Group gridRoot = new Group();
	private Group graphRoot = new Group();
	private Timeline animation;
	private Button play, stop, step, reset;
	private Slider speedSlider, probabilitySlider;
	private ComboBox<String> xmlComboBox;
	private ComboBox<String> shapeComboBox;
	private ComboBox<String> cellStateComboBox;
	private Text cellSocietyText, instructionsText, speedSliderText, probabilitySliderText;
	private SimulationController simulationControl;	
	private ResourceBundle myResources;
	private CheckBox gridLineCheckBox;
	private ToggleGroup colorToggleGroup = new ToggleGroup();
	private RadioButton normal, brighten, darken, saturate, desaturate, grayscale, invert;

	
	private int XSIZE;
	private int YSIZE;

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
	
	public UX(int XSIZE, int YSIZE) {
		myResources = ResourceBundle.getBundle(RESOURCE_FILE_NAME);
		this.XSIZE = XSIZE;
		this.YSIZE = YSIZE;
	}

	public Scene init() {
		scene = new Scene(root, XSIZE, YSIZE, Color.BLACK);
		scene.getStylesheets().add(CSS_FILE_NAME);
		
		buttonInit();
		buttonActionInit();
		speedSliderInit();
		xmlComboBoxInit();
		shapeComboBoxInit();
		displayInstructions();
		displayTitle();
		displaySpeedSliderText();
		displayGridLineCheckBox();
		cellStateComboBoxInit();
		probabilitySliderInit();
		displayProbabilitySliderText();
		displayRadioButtons();
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
		double speedMultiplier = speedSlider.getValue();
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
		String shape = getShape(getShapeComboBoxValue());
		stopSimulation();
		if (!file.equals("NONE CHOSEN")) {
			try{
			simulationControl = new SimulationController();
			simulationControl.initializeSimulation(file, shape);
			resetGridRoot();
			}
			catch (Exception e){
				NoShapeChosen noShapeException = new NoShapeChosen();
				noShapeException.CallDialogBox();
			}
		}
	}

	private void resetGridRoot() {
		root.getChildren().remove(gridRoot);
		Boolean withGridOutlines = gridLineCheckBox.isSelected();
		gridRoot = simulationControl.returnCurrVisualGrid(withGridOutlines, getColorChoice(colorToggleGroup.getSelectedToggle()));
		root.getChildren().add(gridRoot);
		
		simulationControl.setMyLineChartLayout(GRID_START_X-40, GRID_START_Y-185);
		root.getChildren().remove(graphRoot);
		graphRoot = simulationControl.getPopulationChart();
		root.getChildren().add(graphRoot);
	}

	private void advanceGridRoot() {
		root.getChildren().remove(gridRoot);
		gridRoot = simulationControl.returnNextVisualGrid();
		root.getChildren().add(gridRoot);
	}
	
	private void checkSpeed() {
		if (speedSlider.isValueChanging() == true) {
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
		case ("SLIME MOLD"):
			return ("Slime.xml");
		case ("SUGAR AND SPICE"):
			return ("SugarAndSpice.xml");
		case ("INVALID CELL STATE"):
			return ("InvalidCellState.xml");
		}
		return null;
	}
	
	private String getShape(String chosenShape){
		switch(chosenShape) {
		case("SQUARE"):
			return "Square";
		case("TRIANGLE"):
			return "Triangle";
		case("HEXAGON"):
			return "Hexagon";
		default:
			return null;
		}
	}
	
	private ColorScheme getColorChoice(Toggle radioButton){
		switch (radioButton.getUserData().toString()) {
		case ("brighten"):
			return ColorScheme.BRIGHTEN;
		case ("darken"):
			return ColorScheme.DARKEN;
		case ("saturate"):
			return ColorScheme.SATURATE;
		case ("grayscale"):
			return ColorScheme.GRAYSCALE;
		case ("invert"):
			return ColorScheme.INVERT;
		default:
			return ColorScheme.NORMAL;
		}
	}

	/*----------------- Private / Helper Methods -----------------------------*/

	private void xmlComboBoxInit() {
		ObservableList<String> xmlOptions = FXCollections.observableArrayList(myResources.getString("Segregation"), 
				myResources.getString("PredatorPrey"),myResources.getString("Fire"),myResources.getString("GameOfLife"),
				myResources.getString("SlimeMold"), myResources.getString("SugarScape"),
				myResources.getString("NoSimulationType"), myResources.getString("InvalidCellState"));
		xmlComboBox = new ComboBox<String>(xmlOptions);
		xmlComboBox.setValue(myResources.getString("XMLComboBoxText"));
		root.getChildren().add(setControlLayout(xmlComboBox, CONTROLS_SPACING * 5));
	}

	private void shapeComboBoxInit() {
		ObservableList<String> shapeOptions = FXCollections.observableArrayList(myResources.getString("Square"), 
				myResources.getString("Triangle"), myResources.getString("Hexagon"));
		shapeComboBox = new ComboBox<String>(shapeOptions);
		shapeComboBox.setValue(myResources.getString("ShapeComboBoxText"));
		root.getChildren().add(setControlLayout(shapeComboBox, CONTROLS_SPACING * 6));
	}
	
	private void cellStateComboBoxInit(){
		ObservableList<String> stateOptions = FXCollections.observableArrayList(myResources.getString("Random"), 
				myResources.getString("XMLVals"), myResources.getString("Probability"));
		cellStateComboBox = new ComboBox<String>(stateOptions);
		cellStateComboBox.setValue(myResources.getString("StateComboBoxText"));
		root.getChildren().add(setControlLayout(cellStateComboBox, CONTROLS_SPACING*8));
	}
	
	private void speedSliderInit() {
		speedSlider = new Slider(SPEED_SLIDER_MIN, SPEED_SLIDER_MAX, SPEED_SLIDER_DEFAULT);
		speedSlider.setMajorTickUnit(1f);
		root.getChildren().add(setControlLayout(speedSlider, CONTROLS_SPACING * 4));
	}
	
	private void probabilitySliderInit(){
		probabilitySlider = new Slider(PROBABILITY_SLIDER_MIN, PROBAILITY_SLIDER_MAX, PROBABILITY_SLIDER_DEFAULT);
		probabilitySlider.setMajorTickUnit(1f);
		root.getChildren().add(setControlLayout(probabilitySlider, CONTROLS_SPACING * 9));
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
	

	private void displaySpeedSliderText() {
		speedSliderText = new Text(SLIDER_TEXT_X, CONTROLS_START_Y + CONTROLS_SPACING*4, myResources.getString("SliderText"));
		speedSliderText.getStyleClass().add("slider");
		root.getChildren().add(speedSliderText);
	}
	
	private void displayProbabilitySliderText(){
		probabilitySliderText = new Text(SLIDER_TEXT_X-30, CONTROLS_START_Y + CONTROLS_SPACING*9, myResources.getString("ProbabilitySliderText"));
		probabilitySliderText.getStyleClass().add("slider");
		root.getChildren().add(probabilitySliderText);
	}
	
	private void displayGridLineCheckBox(){
		gridLineCheckBox = new CheckBox();
		gridLineCheckBox.setText("GRID LINES");
		root.getChildren().add(setControlLayout(gridLineCheckBox,315));
		gridLineCheckBox.setLayoutX(50);
	}

	private void displayTitle() {
		cellSocietyText = new Text(TITLE_X, TITLE_Y, myResources.getString("DisplayTitle"));
		cellSocietyText.getStyleClass().add("title");
		root.getChildren().add(cellSocietyText);
	}
	
	private void displayRadioButtons(){
		normal = new RadioButton(myResources.getString("normal"));
		brighten = new RadioButton(myResources.getString("brighten"));
		darken = new RadioButton(myResources.getString("darken"));
		saturate = new RadioButton(myResources.getString("saturate"));
		grayscale = new RadioButton(myResources.getString("grayscale"));
		invert = new RadioButton(myResources.getString("invert"));
		
		normal.setUserData("normal");
		brighten.setUserData("brighten");
		darken.setUserData("darken");
		saturate.setUserData("saturate");
		grayscale.setUserData("grayscale");
		invert.setUserData("invert");
		
		addToToggleGroup(normal);
		addToToggleGroup(brighten);
		addToToggleGroup(darken);
		addToToggleGroup(saturate);
		addToToggleGroup(grayscale);
		addToToggleGroup(invert);
		
		setControlLayout(normal, 425);
		setControlLayout(brighten, 450);
		setControlLayout(darken, 475);
		setControlLayout(saturate, 500);
		setControlLayout(grayscale,525);
		setControlLayout(invert, 550);
		
		normal.setSelected(true);
		
		root.getChildren().addAll(normal, brighten, darken, saturate, grayscale, invert);
	}
	
	private void addToToggleGroup(RadioButton button){
		button.setToggleGroup(colorToggleGroup);
	}

	private Control setControlLayout(Control control, int layoutY) {
		control.setLayoutX(CONTROLS_START_X);
		control.setLayoutY(CONTROLS_START_Y + layoutY);
		control.setFocusTraversable(false);
		control.getStyleClass().add("control");
		return control;
	}
	
}

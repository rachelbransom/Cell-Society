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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javafx.scene.text.TextAlignment;
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
	
	private final int TITLE_SIZE = 80, INSTRUCTIONS_SIZE = 60, INSTRUCTIONSX = 10, INSTRUCTIONSY = 300,
			SLIDER_TEXT_SIZE = 10, SLIDER_TEXT_X = 4 * BUTTON_DIMENSIONS + 25, SLIDER_MIN = 1, 
			SLIDER_MAX = 10, SLIDER_DEFAULT = 3, SLIDER_TEXT_Y = Main.YSIZE - BUTTON_DIMENSIONS + 15 ;

	private Scene scene;
	private Group root = new Group();
	private Group gridRoot = new Group();
	private Timeline animation;
	private Button play, stop, step, reset;
	private Slider slider;
	private ComboBox<String> comboBox;
	private Rectangle gridBorder;
	private Text cellSocietyText, instructionsText, sliderText;
	private SimulationController simulationControl;
	
	private ResourceBundle myResources;

	public static final int FRAMES_PER_SECOND = 1;
	private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private static int BUTTON_DIMENSIONS = Main.XSIZE / 10;
	private static final String RESOURCE_FILE_NAME = "resources/DisplayedText";
	public static int GRID_START = Main.YSIZE - Main.XSIZE - BUTTON_DIMENSIONS;

	public String getTitle() {
		return myResources.getString("Title");
	}
	
	public UX() {
		myResources = ResourceBundle.getBundle(RESOURCE_FILE_NAME);
	}

	public Scene init() {
		scene = new Scene(root, Main.XSIZE, Main.YSIZE, Color.BLACK);
		
		buttonInit();
		sliderInit();
		comboBoxInit();
		gridBorderInit();
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
		root.getChildren().addAll(setControlLayout(play, 0, 1), setControlLayout(stop, BUTTON_DIMENSIONS, 1),
				setControlLayout(step, BUTTON_DIMENSIONS * 2, 1), setControlLayout(reset, BUTTON_DIMENSIONS * 3, 1));
	}

	private void playSimulation() {
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
		String file = getFile(getComboBoxValue());
		stopSimulation();
		if (!file.equals("NONE CHOSEN")) {
			root.getChildren().removeAll(instructionsText, gridBorder);

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
		}
		return null;
	}

	/*----------------- Private / Helper Methods -----------------------------*/

	private void comboBoxInit() {
		ObservableList<String> xmlOptions = FXCollections.observableArrayList(myResources.getString("Segregation"), 
				myResources.getString("PredatorPrey"),myResources.getString("Fire"),myResources.getString("GameOfLife"));
		comboBox = new ComboBox<String>(xmlOptions);
		comboBox.setValue(myResources.getString("ComboBoxText"));
		root.getChildren().add(setControlLayout(comboBox, BUTTON_DIMENSIONS * 6, 4));
	}

	private void sliderInit() {
		slider = new Slider(SLIDER_MIN, SLIDER_MAX, SLIDER_DEFAULT);
		slider.setMajorTickUnit(1f);
		root.getChildren().add(setControlLayout(slider, BUTTON_DIMENSIONS * 4, 2));
	}

	private void gridBorderInit() {
		gridBorder = new Rectangle(0, GRID_START, Main.XSIZE, Main.XSIZE);
		gridBorder.setFill(Color.LIGHTGRAY);
		root.getChildren().add(gridBorder);
	}

	private void displayInstructions() {
		instructionsText = new Text(INSTRUCTIONSX, INSTRUCTIONSY, myResources.getString("Instructions"));
		root.getChildren().add(setTextLayout(instructionsText, INSTRUCTIONS_SIZE));
	}

	private String getComboBoxValue() {
		return comboBox.getSelectionModel().getSelectedItem();
	}

	private void displaySliderText() {
		sliderText = new Text(SLIDER_TEXT_X, SLIDER_TEXT_Y, myResources.getString("SliderText"));
		root.getChildren().add(setTextLayout(sliderText, SLIDER_TEXT_SIZE));
	}

	private void displayTitle() {
		cellSocietyText = new Text(0, BUTTON_DIMENSIONS + 10, myResources.getString("DisplayTitle"));
		root.getChildren().add(setTextLayout(cellSocietyText, TITLE_SIZE));
	}

	private Text setTextLayout(Text text, int size) {
		text.setFont(Font.font("Segoe UI Semibold", FontWeight.BOLD, size));
		text.setFill(Color.WHITE);
		text.setTextAlignment(TextAlignment.CENTER);
		return text;
	}

	private Control setControlLayout(Control control, int layoutX, int widthMultiplier) {
		control.setLayoutX(layoutX);
		control.setLayoutY(Main.YSIZE - BUTTON_DIMENSIONS);
		control.setPrefSize(BUTTON_DIMENSIONS * widthMultiplier, BUTTON_DIMENSIONS);
		control.setFocusTraversable(false);
		control.setStyle("-fx-font-size: 12; -fx-base: #1d1d1d");
		return control;
	}
}

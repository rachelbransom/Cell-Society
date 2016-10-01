package cellsociety_team23;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import simulation.SimulationController;

public class UXConfiguration {
	protected final int TITLE_X = 10, TITLE_Y = 100, CONTROLS_START_X = 10, CONTROLS_START_Y = 150,
			CONTROLS_SPACING = 45, INSTRUCTIONSX = 275, INSTRUCTIONSY = 350, SLIDER_TEXT_X = CONTROLS_START_X + 50,
			SLIDER_MIN = 1, SLIDER_MAX = 10, SLIDER_DEFAULT = 3;

	protected Button play, stop, step, reset;
	protected static final String RESOURCE_FILE_NAME = "resources/DisplayedText";
	protected ResourceBundle myResources;
	protected Slider slider;
	protected ComboBox<String> xmlComboBox;
	protected ComboBox<String> shapeComboBox;
	private Text cellSocietyText, instructionsText, sliderText;
	protected SimulationController simulationControl;

	protected void buttonInit() {
		play = new Button(myResources.getString("PlayButton"));
		stop = new Button(myResources.getString("StopButton"));
		step = new Button(myResources.getString("StepButton"));
		reset = new Button(myResources.getString("ResetButton"));

		setControlLayout(play, 0);
		setControlLayout(stop, CONTROLS_SPACING);
		setControlLayout(step, CONTROLS_SPACING * 2);
		setControlLayout(reset, CONTROLS_SPACING * 3);
		
	}
	
	protected ArrayList<Button> getButtons(){
		ArrayList<Button> buttons = new ArrayList<Button>();
		
		buttons.add(play);
		buttons.add(stop);
		buttons.add(step);
		buttons.add(reset);
		
		return buttons;

	}

	protected ComboBox xmlComboBoxInit() {
		ObservableList<String> xmlOptions = FXCollections.observableArrayList(myResources.getString("Segregation"),
				myResources.getString("PredatorPrey"), myResources.getString("Fire"),
				myResources.getString("GameOfLife"), myResources.getString("NoSimulationType"),
				myResources.getString("InvalidCellState"));
		xmlComboBox = new ComboBox<String>(xmlOptions);
		xmlComboBox.setValue(myResources.getString("XMLComboBoxText"));
		setControlLayout(xmlComboBox, CONTROLS_SPACING * 5);
		return xmlComboBox;
		// root.getChildren().add(setControlLayout(xmlComboBox, CONTROLS_SPACING
		// * 5));
	}

	protected ComboBox shapeComboBoxInit() {
		ObservableList<String> shapeOptions = FXCollections.observableArrayList(myResources.getString("Square"),
				myResources.getString("Triangle"));
		shapeComboBox = new ComboBox<String>(shapeOptions);
		shapeComboBox.setValue(myResources.getString("ShapeComboBoxText"));
		setControlLayout(shapeComboBox, CONTROLS_SPACING * 6);
		return shapeComboBox;

	}

	protected Slider sliderInit() {
		slider = new Slider(SLIDER_MIN, SLIDER_MAX, SLIDER_DEFAULT);
		slider.setMajorTickUnit(1f);
		setControlLayout(slider, CONTROLS_SPACING * 4);
		return slider;
		// root.getChildren().add(setControlLayout(slider, CONTROLS_SPACING *
		// 4));
	}

	protected Text displayInstructions() {
		instructionsText = new Text(INSTRUCTIONSX, INSTRUCTIONSY, myResources.getString("Instructions"));
		instructionsText.getStyleClass().add("instructions");
		return instructionsText;
		// root.getChildren().add(instructionsText);
	}

	protected String getXMLComboBoxValue() {
		return xmlComboBox.getSelectionModel().getSelectedItem();
	}

	protected String getShapeComboBoxValue() {
		return shapeComboBox.getSelectionModel().getSelectedItem();
	}

	protected Text displaySliderText() {
		sliderText = new Text(SLIDER_TEXT_X, CONTROLS_START_Y + CONTROLS_SPACING * 4,
				myResources.getString("SliderText"));
		sliderText.getStyleClass().add("slider");
		return sliderText;
		// root.getChildren().add(sliderText);
	}

	protected Text displayTitle() {
		cellSocietyText = new Text(TITLE_X, TITLE_Y, myResources.getString("DisplayTitle"));
		cellSocietyText.getStyleClass().add("title");
		return cellSocietyText;
		// root.getChildren().add(cellSocietyText);
	}

	private void setControlLayout(Control control, int layoutY) {
		control.setLayoutX(CONTROLS_START_X);
		control.setLayoutY(CONTROLS_START_Y + layoutY);
		control.setFocusTraversable(false);
		control.getStyleClass().add("control");
	}

	protected String getFile(String chosenFileName) {
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

}

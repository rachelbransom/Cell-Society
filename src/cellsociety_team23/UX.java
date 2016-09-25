package cellsociety_team23;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
//import javax.swing.JComboBox;
//
//import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Slider;
//import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import simulation.SimulationController;

public class UX {
	private final String TITLE = "Cell Society";

	private final int titleSize = 80, instructionsSize = 60, instructionsX = 35, instructionsY = 300,
			sliderTextSize = 10, sliderTextX = 4*BUTTON_DIMENSIONS+25, sliderTextY = Main.YSIZE-BUTTON_DIMENSIONS+15;
	
	private Scene scene;
	private Group root = new Group();
	private Group gridRoot = new Group();
	private Timeline animation;
	private Button start, stop, step, reset;
	private ComboBox<String> comboBox;
	private Rectangle gridBorder;
	private Text cellSocietyText, instructionsText, sliderText;
	private SimulationController simulationControl;
	
	public static final int FRAMES_PER_SECOND = 1;
	private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private static int BUTTON_DIMENSIONS = Main.XSIZE/10;
	public static int GRID_START = Main.YSIZE-Main.XSIZE-BUTTON_DIMENSIONS;
	
	public String getTitle(){
		return TITLE;
	}
	
	public Scene init(int width, int height) {
		scene = new Scene(root, width, height, Color.BLACK);
		buttonInit();
		//textFieldInit();
		sliderInit();
		
		comboBoxInit();
		gridBorderInit();
		displayInstructions();
		displayTitle();
		displaySliderText();
		root.getChildren().add(gridRoot);
		return scene;
	}
	
	private Control setControlLayout(Control control, int layoutX, int widthMultiplier) {
		control.setLayoutX(layoutX);
		control.setLayoutY(Main.YSIZE-BUTTON_DIMENSIONS);
		control.setPrefSize(BUTTON_DIMENSIONS*widthMultiplier, BUTTON_DIMENSIONS);
		control.setFocusTraversable(false);
		control.setStyle("-fx-font-size: 12; -fx-base: #1d1d1d");
		return control;
	}
	
	private void buttonInit() {
		start = new Button("PLAY");
		stop = new Button("STOP");
		step = new Button("STEP");
		reset = new Button("RESET");
		
		start.setOnAction((event) -> {
			KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY),
                    e -> step());
			animation = new Timeline();
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.getKeyFrames().add(frame);
			animation.play();	
		});
		stop.setOnAction((event) -> {
			stopSimulation();				
		});
		step.setOnAction((event) -> {			
			step();
		});
		reset.setOnAction((event) -> {
			String file = getFile(getComboBoxValue());
			stopSimulation();	
			if (!file.equals("NONE CHOSEN")){
				simulationControl = new SimulationController();
				simulationControl.initializeSimulation(file);
				resetGridRoot();
			}
		});		
		root.getChildren().addAll(setControlLayout(start,0,1), setControlLayout(stop,BUTTON_DIMENSIONS,1),
				setControlLayout(step,BUTTON_DIMENSIONS*2,1),setControlLayout(reset,BUTTON_DIMENSIONS*3,1));
		
	}

	private void stopSimulation() {
		if (animation != null){
			animation.stop();
		}
	}

	private void step() {
		if (simulationControl != null){
			resetGridRoot();
		}
	}
	
	private void resetGridRoot() {
		root.getChildren().remove(gridRoot);
		gridRoot = simulationControl.returnVisualGrid();
		root.getChildren().add(gridRoot);
	}
	
	
	private void sliderInit(){
		Slider slider = new Slider(1, 3, 5);
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(false);
		slider.setMajorTickUnit(0.25f);
		slider.setBlockIncrement(0.1f);
		root.getChildren().add(setControlLayout(slider,BUTTON_DIMENSIONS*4,2));
	}
	
	
	private void displaySliderText(){
		sliderText = new Text(sliderTextX, sliderTextY, "Adjust speed");
		sliderText.setFont(Font.font("Segoe UI Semibold", sliderTextSize));
		sliderText.setFill(Color.WHITE);
		root.getChildren().add(sliderText);
	}
	
	private void comboBoxInit(){
		ObservableList<String> xmlOptions = FXCollections.observableArrayList(
				"SEGREGATION",
				"PREDATOR-PREY",
				"FIRE",
				"GAME OF LIFE"
			);
		comboBox = new ComboBox<String>(xmlOptions);		
		comboBox.setValue("CHOOSE XML FILE");
		root.getChildren().add(setControlLayout(comboBox, BUTTON_DIMENSIONS*6,4));
		

	}
	
	private String getComboBoxValue(){
		return comboBox.getSelectionModel().getSelectedItem();

	}
	
	private void gridBorderInit() {
		gridBorder = new Rectangle(0,GRID_START, Main.XSIZE,Main.XSIZE);
		gridBorder.setFill(Color.LIGHTGRAY);
		root.getChildren().add(gridBorder);
	}
	
	private void displayTitle() {
		cellSocietyText = new Text(0,BUTTON_DIMENSIONS + 10,"CELL SOCIETY");
		cellSocietyText.setFont(Font.font("Segoe UI Semibold", FontWeight.BOLD, titleSize));
		cellSocietyText.setFill(Color.WHITE);
		root.getChildren().add(cellSocietyText);
	}
	
	private void displayInstructions(){
		instructionsText = new Text(instructionsX, instructionsY, "Please select \n an XML file \n and press 'RESET'");
		instructionsText.setFont(Font.font("Segoe UI Semibold", instructionsSize));
		instructionsText.setTextAlignment(TextAlignment.CENTER);
		instructionsText.setFill(Color.WHITE);
		root.getChildren().add(instructionsText);
	}
	
	
	private String getFile(String chosenFileName){
		switch (chosenFileName){
			case ("SEGREGATION"):
				return "Segregation.xml";
			case ("PREDATOR-PREY"):
				return "Wator.xml";
			case ("FIRE"):
				return "Fire.xml";
			case("GAME OF LIFE"):
				return "Life.xml";
			case("CHOOSE XML FILE"):
				return "NONE CHOSEN";
		}
		return null;
	}
	
}

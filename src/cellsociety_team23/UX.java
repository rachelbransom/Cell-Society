package cellsociety_team23;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import simulation.SimulationController;

public class UX {
	private final String TITLE = "Cell Society";
	private final int titleSize = 80;
	private Scene scene;
	private Group root = new Group();
	private Button start, stop, step, reset;
	
	private TextField speedTextField;
	private ComboBox<String> comboBox;
	private Rectangle gridBorder;
	private Text cellSocietyText;
	
	private SimulationController mySimulation;
	
	private static int BUTTON_DIMENSIONS = Main.XSIZE/10;
	public static int GRID_START = Main.YSIZE-Main.XSIZE-BUTTON_DIMENSIONS;
	
	public String getTitle(){
		return TITLE;
	}
	
	public Scene init(int width, int height) {
		scene = new Scene(root, width, height, Color.BLACK);
		buttonInit();
		textFieldInit();
		comboBoxInit();
		gridBorderInit();
		displayTitle();
		// call grid visual
		
		return scene;
	}
	
	private Control setControlLayout(Control control, int layoutX, int widthMultiplier) {
		control.setLayoutX(layoutX);
		control.setLayoutY(Main.YSIZE-BUTTON_DIMENSIONS);
		control.setPrefSize(BUTTON_DIMENSIONS*widthMultiplier, BUTTON_DIMENSIONS);
		control.setFocusTraversable(false);
		control.setStyle("-fx-font: 11 Segoe UI Semibold; -fx-base: #1d1d1d");
		return control;
	}
	
	private void buttonInit() {
		start = new Button("START");
		stop = new Button("STOP");
		step = new Button("STEP");
		reset = new Button("RESET");
		
		start.setOnAction((event) -> {
			//start
		});
		stop.setOnAction((event) -> {
			//stop
		});
		step.setOnAction((event) -> {
			//step
		});
		reset.setOnAction((event) -> {
		});
		
		root.getChildren().addAll(setControlLayout(start,0,1), setControlLayout(stop,BUTTON_DIMENSIONS,1),
				setControlLayout(step,BUTTON_DIMENSIONS*2,1),setControlLayout(reset,BUTTON_DIMENSIONS*3,1));
		
	}
		
	private void textFieldInit(){
		speedTextField = new TextField();
		speedTextField.setPromptText("ENTER SPEED");
		root.getChildren().add(setControlLayout(speedTextField,BUTTON_DIMENSIONS*4,2));
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
	
	private void gridBorderInit() {
		gridBorder = new Rectangle(0,Main.YSIZE-Main.XSIZE-BUTTON_DIMENSIONS, Main.XSIZE,Main.XSIZE);
		gridBorder.setFill(Color.LIGHTGRAY);
		root.getChildren().add(gridBorder);
	}
	
	private void displayTitle() {
		cellSocietyText = new Text(0,BUTTON_DIMENSIONS + 10,"CELL SOCIETY");
		cellSocietyText.setFont(Font.font("Segoe UI Semibold", FontWeight.BOLD, titleSize));
		cellSocietyText.setFill(Color.WHITE);
		root.getChildren().add(cellSocietyText);
	}
	
}

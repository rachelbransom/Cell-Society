package cellsociety_team23;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.paint.Color;

public class UX {
	private final String TITLE = "Cell Society";
	private Scene scene;
	private Group root = new Group();
	private Button start, stop, step, reset;
	private int buttonDimensions = Main.SIZE/10;
	private TextField speedTextField;
	private ComboBox comboBox;
	
	public String getTitle(){
		return TITLE;
	}
	
	public Scene init(int width, int height) {
		scene = new Scene(root, width, height, Color.BLACK);
		buttonInit();
		textFieldInit();
		comboBoxInit();
		return scene;
	}
	
	private Control setControlLayout(Control control, int layoutX, int widthMultiplier) {
		control.setLayoutX(layoutX);
		control.setLayoutY(Main.SIZE-buttonDimensions);
		control.setPrefSize(buttonDimensions*widthMultiplier, buttonDimensions);
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
			//reset
		});
		
		root.getChildren().addAll(setControlLayout(start,0,1), setControlLayout(stop,buttonDimensions,1),
				setControlLayout(step,buttonDimensions*2,1),setControlLayout(reset,buttonDimensions*3,1));
	}
	
	private void textFieldInit(){
		speedTextField = new TextField();
		speedTextField.setPromptText("ENTER SPEED");
		root.getChildren().add(setControlLayout(speedTextField,buttonDimensions*4,2));
	}
	
	private void comboBoxInit(){
		ObservableList<String> xmlOptions = FXCollections.observableArrayList(
				"SEGREGATION",
				"PREDATOR-PREY",
				"FIRE",
				"GAME OF LIFE"
			);
		comboBox = new ComboBox(xmlOptions);
		comboBox.setValue("CHOOSE XML FILE");
		root.getChildren().add(setControlLayout(comboBox, buttonDimensions*6,4));
	}
	
	
}

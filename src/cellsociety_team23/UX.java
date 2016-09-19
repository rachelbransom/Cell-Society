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

public class UX {
	private final String TITLE = "Cell Society";
	private final int titleSize = 88;
	private Scene scene;
	private Group root = new Group();
	private Button start, stop, step, reset;
	private int buttonDimensions = Main.XSIZE/10;
	private TextField speedTextField;
	private ComboBox<String> comboBox;
	private Rectangle gridBorder;
	private Text cellSocietyText;
	
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
		control.setLayoutY(Main.YSIZE-buttonDimensions);
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
		comboBox = new ComboBox<String>(xmlOptions);
		comboBox.setValue("CHOOSE XML FILE");
		root.getChildren().add(setControlLayout(comboBox, buttonDimensions*6,4));
	}
	
	private void gridBorderInit() {
		gridBorder = new Rectangle(0,Main.YSIZE-Main.XSIZE-buttonDimensions, Main.XSIZE,Main.XSIZE);
		gridBorder.setFill(Color.LIGHTGRAY);
		root.getChildren().add(gridBorder);
	}
	
	private void displayTitle() {
		cellSocietyText = new Text(0,buttonDimensions+10,"CELL SOCIETY");
		cellSocietyText.setFont(Font.font("Segoe UI Semibold", FontWeight.BOLD, titleSize));
		cellSocietyText.setFill(Color.WHITE);
		root.getChildren().add(cellSocietyText);
	}
	
}

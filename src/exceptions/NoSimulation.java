package exceptions;

import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class NoSimulation extends ExceptionConfirguration {
	private ResourceBundle myResources = ResourceBundle.getBundle("resources/DisplayedText");

	public NoSimulation(){
		super("Chosen file has no simulation type");
	}
	
	public void callDialogBox(){
		showDialogBox("No simulation type!", "Error with parsing XML file",
				"The file you chose doesn't have a simulation type. Please select"
				+ "a different file.");
	}
}

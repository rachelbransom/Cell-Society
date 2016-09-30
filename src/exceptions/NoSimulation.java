package exceptions;

import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class NoSimulation extends Exception {
	//private ResourceBundle myResources = ResourceBundle.getBundle("resources/DisplayedText");

	public NoSimulation(){
		
		super("Chosen file has no simulation type");
		
	}
	
	public void showDialogBox(){
		//need to make resources file work with this
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("No Simulation Type!");
		alert.setHeaderText("Error with Parsing XML File");
		alert.setContentText("The file you selected seems to not have a simulation type. Please select a different file.");
		alert.show();
	}
	
}

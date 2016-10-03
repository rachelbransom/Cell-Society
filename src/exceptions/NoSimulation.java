package exceptions;

import java.util.ResourceBundle;


public class NoSimulation extends ExceptionConfiguration {

	public NoSimulation(){
		super("Chosen file has no simulation type");
	}
	
	public void callDialogBox(){
		showDialogBox("No simulation type!", "Error with parsing XML file",
				"The file you chose doesn't have a simulation type. Please select"
				+ "a different file.");
	}
}

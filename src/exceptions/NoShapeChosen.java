package exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class NoShapeChosen extends ExceptionConfiguration{
	public NoShapeChosen(){
		super("No shape selected");
	}
	
	public void CallDialogBox() {
		showDialogBox("Error with starting the simulation!", "No shape chosen", 
				"Please choose a cell shape before resting the simulation.");
	}
	
}



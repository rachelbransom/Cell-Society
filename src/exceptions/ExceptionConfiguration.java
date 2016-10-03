package exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

//@author Rachel Bransom

public class ExceptionConfiguration extends Exception{

	public ExceptionConfiguration(String string){
		super(string);
	}
	
	public void showDialogBox(String title, String header, String content){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.show();
	}
}

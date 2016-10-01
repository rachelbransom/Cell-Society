package exceptions;

public class InvalidCellState extends ExceptionConfirguration {
	public InvalidCellState(){
		super("Chosen file contains an invalid cell state");
	}
	
	public void callDialogBox(){
		showDialogBox("Invalid cell state", "Error with parsing XML file",
				"The file chosen contains an invalid cell state. Please select"
				+ " a different XML file");
	}
}

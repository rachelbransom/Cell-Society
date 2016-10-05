package exceptions;

//@author Rachel Bransom

public class InvalidUserInput extends ExceptionConfiguration{
	public InvalidUserInput(){
		super("No shape selected");
	}
	
	public void CallDialogBox() {
		showDialogBox("Error with starting the simulation!", "No shape or no file chosen", 
				"Please choose a cell shape AND a file before resting the simulation.");
	}
	
}



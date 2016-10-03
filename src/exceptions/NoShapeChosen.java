package exceptions;

//@author Rachel Bransom

public class NoShapeChosen extends ExceptionConfiguration{
	public NoShapeChosen(){
		super("No shape selected");
	}
	
	public void CallDialogBox() {
		showDialogBox("Error with starting the simulation!", "No shape or no file chosen", 
				"Please choose a cell shape AND a file before resting the simulation.");
	}
	
}



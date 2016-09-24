package cellsociety_team23;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	private UX userEx;
	public static int XSIZE = 585;
	public static int YSIZE = 720;
	
	public void start(Stage stage) {
		userEx = new UX();
		stage.setTitle(userEx.getTitle());
		stage.setScene(userEx.init(XSIZE, YSIZE));
		stage.setResizable(true);
		stage.show();
	}
	
	public static void main(String[] args){
		launch(args);
	}
}

//0.8125
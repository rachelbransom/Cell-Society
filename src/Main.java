import cellsociety_team23.ApplicationController;
import cellsociety_team23.Toolbar;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	private Toolbar toolbar;
	public static int XSIZE = 750;
	public static int YSIZE = 750;
	
	public void start(Stage stage) {
		toolbar = new Toolbar(XSIZE, YSIZE);
		stage.setTitle(toolbar.getTitle());
		stage.setScene(toolbar.init());
		stage.setResizable(false);
		stage.show();
	}
	
	public static void main(String[] args){
		launch(args);
	}
}

import cellsociety_team23.UX;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	private UX userEx;
	public static int XSIZE = 750;
	public static int YSIZE = 750;
	
	public void start(Stage stage) {
		userEx = new UX(XSIZE, YSIZE);
		stage.setTitle(userEx.getTitle());
		stage.setScene(userEx.init());
		stage.setResizable(false);
		stage.show();
	}
	
	public static void main(String[] args){
		launch(args);
	}
}

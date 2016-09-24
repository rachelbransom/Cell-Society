package simulation;
import cellUtil.Grid;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class SimulationVisualizer {
	private int myGridSize;
	private int myPixelSize;
	private Grid myCellGrid;
	private Rectangle[][] myRectGrid;
	private Group root;
	
	public SimulationVisualizer (int gridPixelSize, Group rootToSet){
		rootToSet = root;
		myPixelSize = gridPixelSize;
	}
	
	private Group showSimulation(){
		return root;
	}
	
	void update(){
		
	}

	void setCurrentGrid(Grid grid){
		myCellGrid = new Grid( grid );
		myGridSize = grid.getSize();
		
	}
	
	void initRectGrid(){
		for (int i=0; i<myGridSize; i++){
			for (int j=0; i<myGridSize; j++){
				myRectGrid[i][j] = new Rectangle(myPixelSize/myGridSize,myPixelSize/myGridSize);
				root.getChildren().add(myRectGrid[i][j]);
			}
		}
	}
	SimulationVisualizer(int size){
		initRectGrid(size);
	}

	Node returnVisualGrid(){
		return null;
	}

	void updateVisuals(Color[][] colorGrid){

		updateGridColor(colorGrid);


	}

	private void updateGridColor(Color[][] colorGrid){

		for (int i = 0; i < myRectGrid.length; i++) {
			for (int j = 0; j < myRectGrid.length; j++) {
				myRectGrid[i][j].setFill( colorGrid[i][j] );
			}
		}

	}

	private void initRectGrid(int size){
		myRectGrid = new Rectangle[size][size];
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				myRectGrid[i][j] = new Rectangle();
			}
		}	
	}
	
}

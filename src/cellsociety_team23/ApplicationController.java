package cellsociety_team23;

import cellStateConfigurationType.ConfigurationType;
import exceptions.InvalidUserInput;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.util.Duration;
import simulation.SimulationController;
import simulationColorScheme.ColorScheme;

/**
 * This entire file is part of my masterpiece 
 * RACHEL BRANSOM
 */

public class ApplicationController {

	private Group applicationControllerRoot = new Group(), gridRoot = new Group(), graphRoot = new Group();
	private Timeline animation;
	private SimulationController simulationControl;

	public static int GRID_START_X = 250;
	public static int GRID_START_Y = 300;
	public static int GRID_SIZE = 430;
	private static final int FRAMES_PER_SECOND = 1;
	private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private static final int GRAPH_START_X = GRID_START_X - 40;
	private static final int GRAPH_START_Y = GRID_START_Y - 185;

	public ApplicationController(Group simulationRoot) {
		this.applicationControllerRoot = simulationRoot;
	}

	public void playSimulation(Double speedMultiplier) {
		KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY / speedMultiplier), e -> step());

		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	public void stopSimulation() {
		if (animation != null) {
			animation.stop();
		}
	}

	public void step() {
		if (simulationControl != null) {
			advanceGridRoot();
		}
	}

	public void resetSimulation(String XMLFileChosenByUser, String shapeChosenByUser,
			ConfigurationType configTypeChosenByUser, ColorScheme colorSchemeChosenByUser, Boolean gridLinesVisible) {

		stopSimulation();

		initializeSimulationControl(XMLFileChosenByUser, shapeChosenByUser, configTypeChosenByUser);
		resetGridRoot(colorSchemeChosenByUser, gridLinesVisible);
		resetGraph();
	}

	private void initializeSimulationControl(String XMLFileChosenByUser, String shapeChosenByUser,
			ConfigurationType configTypeChosenByUser) {
		simulationControl = new SimulationController();

		if (!XMLFileChosenByUser.equals("NONE CHOSEN")) {
			try {
				simulationControl.initializeSimulation(XMLFileChosenByUser, shapeChosenByUser, configTypeChosenByUser);
			} catch (Exception e) {
				System.out.println(
						"INAVLID USER INPUT XML file: " + XMLFileChosenByUser + ", shape chosen: " + shapeChosenByUser);
				throwException();
			}
		}
	}

	private void resetGridRoot(ColorScheme colorSchemeChosenByUser, Boolean gridLinesVisible) {
		removeFromRoot(gridRoot);
		gridRoot = simulationControl.returnCurrVisualGrid(gridLinesVisible, colorSchemeChosenByUser);
		addToRoot(gridRoot);
	}

	private void advanceGridRoot() {
		removeFromRoot(gridRoot);
		gridRoot = simulationControl.returnNextVisualGrid();
		addToRoot(gridRoot);
	}

	private void resetGraph() {
		simulationControl.setMyLineChartLayout(GRAPH_START_X, GRAPH_START_Y);

		removeFromRoot(graphRoot);
		graphRoot = simulationControl.getPopulationChart();
		addToRoot(graphRoot);
	}

	private void throwException() {
		InvalidUserInput invalidInputException = new InvalidUserInput();
		invalidInputException.CallDialogBox();
	}

	// these two classes added for readability throughout code
	private void removeFromRoot(Group root) {
		applicationControllerRoot.getChildren().remove(root);
	}

	private void addToRoot(Group root) {
		applicationControllerRoot.getChildren().add(root);
	}
}

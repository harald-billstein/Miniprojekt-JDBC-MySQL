package main;

import controller.TheFirmController;
import javafx.application.Application;
import javafx.stage.Stage;
import view.ApplicationGUI;

public class Main extends Application {

	public static void main(String[] args) {
		// TURN OFF HIBERNATE LOGGER
		//java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		// TheFirm theFirm = new TheFirm();
		// theFirm.start();

		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		TheFirmController theFirmController = new TheFirmController();
		theFirmController.start();
		ApplicationGUI applicationGUI = new ApplicationGUI();
		theFirmController.setGui(applicationGUI);
		
		applicationGUI.setObserver(theFirmController);
		applicationGUI.setPrimaryStage(primaryStage);
		applicationGUI.start();
		applicationGUI.getCenterTable().setItems(theFirmController.getEmployees());
	}
}
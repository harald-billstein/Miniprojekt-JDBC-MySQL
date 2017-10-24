package main;

import controller.TheFirmController;
import javafx.application.Application;
import javafx.stage.Stage;
import view.ApplicationGUI;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		TheFirmController theFirmController = new TheFirmController();
		theFirmController.start();
		ApplicationGUI applicationGUI = new ApplicationGUI();
		theFirmController.setGui(applicationGUI);

		applicationGUI.setObservers(theFirmController.getObservers());
		applicationGUI.setPrimaryStage(primaryStage);
		applicationGUI.start();
		applicationGUI.getCenterTable().setItems(theFirmController.getEmployees());
	}
}
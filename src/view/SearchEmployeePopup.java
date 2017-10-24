package view;

import controller.TheFirmController.Observers;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SearchEmployeePopup extends AbstractPopup {

  private Stage popupStage;
  private Scene scene;
  private Label employeeNameLabel, errorLabel;
  private TextField employeeNameInput;
  private HBox topBox, middleBox, bottomBox;
  private VBox mainBox;
  private final static int PREFERRED_POPUP_WIDTH = 600;
  private final static int PREFERRED_POPUP_HEIGHT = 200;

  public SearchEmployeePopup(Stage primaryStage, Observers observers) {
    super("Search", "PopupSearchEmployeeConfirmButton", observers);
    popupStage = new Stage();
    popupStage.initOwner(primaryStage);
  }

  public void createSearchEmployeePopupWindow() {
    createPopupResources();
    getCancelButton().setOnAction((event -> popupStage.close()));
    setupPopupLayout();
    setupAlignments();
    mainBox.setPrefSize(PREFERRED_POPUP_WIDTH, PREFERRED_POPUP_HEIGHT);
    popupStage.setScene(scene);
    popupStage.initModality(Modality.APPLICATION_MODAL);
    popupStage.show();
  }

  private void createPopupResources() {
    mainBox = new VBox();
    scene = new Scene(mainBox);
    employeeNameLabel = new Label("Search for employee");
    errorLabel = new Label();
    employeeNameInput = new TextField("Name");
    topBox = new HBox(employeeNameLabel, employeeNameInput);
    middleBox = new HBox(errorLabel);
    bottomBox = new HBox(getConfirmButton(), getCancelButton());
  }

  //TODO: Change method name
  private void setupPopupLayout() {
    mainBox.getChildren().addAll(topBox, middleBox, bottomBox);
  }

  private void setupAlignments() {
    topBox.setPadding(new Insets(20, 0, 20, 0));
    topBox.setAlignment(Pos.CENTER);
    middleBox.setPadding(new Insets(0, 0, 20, 0));
    middleBox.setAlignment(Pos.CENTER);
    bottomBox.setAlignment(Pos.CENTER);
  }

  public void closePopup() {
    popupStage.close();
  }

  public String getEmployeeNameInput() {
    return employeeNameInput.getText();
  }

  public void setErrorLabelText(String errorMessage) {
    errorLabel.setText(errorMessage);
  }
}

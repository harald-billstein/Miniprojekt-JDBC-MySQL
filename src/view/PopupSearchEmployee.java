package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupSearchEmployee extends VBox implements PopupInterface {

  private Stage popupStage;
  private Scene scene;
  private Button searchButton;
  private Button cancelButton;
  private Label employeeNameLabel;
  private TextField employeeNameInput;
  private HBox topBox;
  private HBox bottomBox;
  private final static int buttonWidth = 100;
  private final static int buttonHeight = 50;
  private final static String buttonFontSize = "-fx-font-size: 20";
  private EventHandler<ActionEvent> eventHandler;

  public PopupSearchEmployee(Stage window) {
    popupStage = new Stage();
    popupStage.initOwner(window);
  }

  public void createPopupSearchEmployeeWindow() {
    createPopupResources();
    createAbortButton();
    createConfirmButton();
    setupPopupLayout();
    setButtonLook();
    setupTopBox();
    setupBottomBox();
    setPrefSize(600, 400);
    popupStage.setScene(scene);
    popupStage.initModality(Modality.APPLICATION_MODAL);
    popupStage.show();
  }

  private void createPopupResources() {
    scene = new Scene(this);
    employeeNameLabel = new Label("Search for employee");
    employeeNameInput = new TextField("Name");
    topBox = new HBox();
    bottomBox = new HBox();
  }

  //TODO: Change method name
  private void setupPopupLayout() {
    getChildren().addAll(topBox, bottomBox);
  }

  private void setupTopBox() {
    topBox.getChildren().addAll(employeeNameLabel, employeeNameInput);
    topBox.setPadding(new Insets(25, 0, 25, 0));
    topBox.setAlignment(Pos.CENTER);
  }

  private void setupBottomBox() {
    bottomBox.getChildren().addAll(searchButton, cancelButton);
    bottomBox.setAlignment(Pos.CENTER);
  }

  private void setButtonLook() {
    searchButton.setPrefSize(buttonWidth, buttonHeight);
    cancelButton.setPrefSize(buttonWidth, buttonHeight);
    searchButton.setStyle(buttonFontSize);
    cancelButton.setStyle(buttonFontSize);
  }

//  private void setTextFieldsLook() {
//    employeeNameLabel.setPrefSize();
//    employeeNameInput.setStyle("-fx-font-size: 16");
//  }

  public void setObserver(EventHandler<ActionEvent> eventHandler) {
    this.eventHandler = eventHandler;
  }

  public void createConfirmButton() {
    searchButton = new Button("Search");
    searchButton.setId("SearchPopupConfirmButton");
    searchButton.setOnAction(eventHandler);
  }

  public void createAbortButton() {
    cancelButton = new Button("Cancel");
    cancelButton.setId("SearchPopupCancelButton");
    cancelButton.setOnAction((event -> closePopup()));
  }

  public void closePopup() {
    popupStage.close();
  }

  public String getEmployeeNameInput() {
    return employeeNameInput.getText();
  }
}

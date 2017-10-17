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

public class PopupSearchEmployee extends AbstractPopup implements PopupInterface {

  private Stage popupStage;
  private Scene scene;
  private Button searchButton;
  private Label employeeNameLabel, errorLabel;
  private TextField employeeNameInput;
  private HBox topBox, middleBox, bottomBox;
  private EventHandler<ActionEvent> eventHandler;
  private VBox vBox;
  private final static int buttonWidth = 100;
  private final static int popupWidth = 600;
  private final static int popupHeight = 200;

  public PopupSearchEmployee(Stage primaryStage) {
    super("Cancel", "PopupSearchEmployeeCancel");
    popupStage = new Stage();
    popupStage.initOwner(primaryStage);
  }

  public void createPopupSearchEmployeeWindow() {
    createPopupResources();
    //createCancelButton();
    getCancelButton().setOnAction((event -> popupStage.close()));
    createConfirmButton();
    setupPopupLayout();
    setButtonLook();
    setupTopBox();
    setupMiddleBox();
    setupBottomBox();
    vBox.setPrefSize(popupWidth, popupHeight);
    popupStage.setScene(scene);
    popupStage.initModality(Modality.APPLICATION_MODAL);
    popupStage.show();
  }

  private void createPopupResources() {
    vBox = new VBox();
    scene = new Scene(vBox);
    employeeNameLabel = new Label("Search for employee");
    errorLabel = new Label();
    employeeNameInput = new TextField("Name");
    topBox = new HBox();
    middleBox = new HBox();
    bottomBox = new HBox();
  }

  //TODO: Change method name
  private void setupPopupLayout() {
    vBox.getChildren().addAll(topBox, middleBox, bottomBox);
  }

  private void setupTopBox() {
    topBox.getChildren().addAll(employeeNameLabel, employeeNameInput);
    topBox.setPadding(new Insets(20, 0, 20, 0));
    topBox.setAlignment(Pos.CENTER);
  }

  private void setupMiddleBox() {
    middleBox.getChildren().add(errorLabel);
    middleBox.setPadding(new Insets(0, 0, 20, 0));
    middleBox.setAlignment(Pos.CENTER);
  }

  private void setupBottomBox() {
    bottomBox.getChildren().addAll(searchButton, getCancelButton());
    bottomBox.setAlignment(Pos.CENTER);
  }

  private void setButtonLook() {
    searchButton.setMinWidth(buttonWidth);
    getCancelButton().setMinWidth(buttonWidth);
  }

  public void setObserver(EventHandler<ActionEvent> eventHandler) {
    this.eventHandler = eventHandler;
  }

  public void createConfirmButton() {
    searchButton = new Button("Search");
    searchButton.setId("SearchPopupConfirmButton");
    searchButton.setOnAction(eventHandler);
  }

/*  public void createCancelButton() {
    cancelButton = new Button("Cancel");
    cancelButton.setId("SearchPopupCancelButton");
    cancelButton.setOnAction((event -> popupStage.close()));
  }*/

  public void closePopup() {
    popupStage.close();
  }

  public String getEmployeeNameInput() {
    return employeeNameInput.getText();
  }

  public void toggleErrorLabelText() {
    errorLabel.setText("Error. Empty input");
  }
}

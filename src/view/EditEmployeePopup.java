package view;

import controller.TheFirmController.Observers;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditEmployeePopup extends AbstractPopup {

  private Stage popupStage;
  private Scene scene;
  private Label errorLabel;
  private TextField[] employeeDataFields = new TextField[4];
  private String[] labelStrings = {"First name:", "Last name:", "Salary:"};
  private Label[] labels = new Label[3];
  private HBox[] inputBoxes = new HBox[3];
  private VBox mainBox;
  private HBox buttonBox;
  private final static int PREFERRED_POPUP_WIDTH = 600;
  private final static int PREFERRED_POPUP_HEIGHT = 200;

  public EditEmployeePopup(Stage primaryStage, Observers observers) {
    super("UpdateEmployeeCancelButton", "Update Employee",
        "PopupEditEmployeeConfirmButton", observers);
    popupStage = new Stage();
    popupStage.initOwner(primaryStage);
  }

  public void createEditEmployeePopup() {
    createPopupResources();
    getCancelButton().setOnAction((event -> popupStage.close()));
    setupPopupLayout();
    mainBox.setPrefSize(PREFERRED_POPUP_WIDTH, PREFERRED_POPUP_HEIGHT);
    popupStage.setScene(scene);
    popupStage.initModality(Modality.APPLICATION_MODAL);
    popupStage.show();
  }

  private void createPopupResources() {
    mainBox = new VBox();
    scene = new Scene(mainBox);
    for (int i = 0; i < labels.length; i++) {
      labels[i] = new Label(labelStrings[i]);
      employeeDataFields[i] = new TextField(labelStrings[i]);
      inputBoxes[i] = new HBox(labels[i], employeeDataFields[i]);
      employeeDataFields[i].setId("Field" + i);
    }
    buttonBox = new HBox(getConfirmButton(), getCancelButton());
    errorLabel = new Label();
  }

  private void setupPopupLayout() {
    for (HBox box : inputBoxes) {
      box.setAlignment(Pos.CENTER);
      mainBox.getChildren().add(box);
    }
    //Sets padding for department Id box
    inputBoxes[2].setPadding(new Insets(0, 0, 20, 0));
    mainBox.getChildren().addAll(errorLabel, buttonBox);
    buttonBox.setAlignment(Pos.CENTER);
  }

  public TextField[] getEmployeeDataArray() {
    return employeeDataFields;
  }

  public Label getErrorLabel() {
    return errorLabel;
  }

  public void closePopup() {
    popupStage.close();
  }
}

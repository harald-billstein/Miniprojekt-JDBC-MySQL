package view;

import java.util.LinkedList;
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
import model.Pair;

public class AddEmployeePopup extends AbstractPopup {

  private Stage popupStage;
  private Scene scene;
  private Label errorLabel;
  private TextField[] employeeDataFields = new TextField[4];
  private String[] labelStrings = {"First name:", "Last name:", "Salary:", "Department ID:"};
  private Label[] labels = new Label[4];
  private HBox[] inputBoxes = new HBox[4];
  private VBox mainBox;
  private HBox buttonBox;
  private final static int PREFERRED_POPUP_WIDTH = 600;
  private final static int PREFERRED_POPUP_HEIGHT = 200;

  public AddEmployeePopup(Stage primaryStage, EventHandler<ActionEvent> eventHandler) {
    super("AddEmployeeCancelButton", "Add Employee",
        "AddEmployeeConfirmButton", eventHandler);
    popupStage = new Stage();
    popupStage.initOwner(primaryStage);
  }

  public void createAddEmployeePopup() {
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
    for (int i = 0; i < 4; i++) {
      labels[i] = new Label(labelStrings[i]);
      employeeDataFields[i] = new TextField(labelStrings[i]);
      inputBoxes[i] = new HBox(labels[i], employeeDataFields[i]);
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
    inputBoxes[3].setPadding(new Insets(0, 0, 20, 0));
    mainBox.getChildren().addAll(errorLabel, buttonBox);
    buttonBox.setAlignment(Pos.CENTER);
  }

  public LinkedList<Pair> getEmployeeData() {

    //TODO: CHANGE TO EMPLOYEE MODEL IN CONTROLLER
    Pair firstPair = new Pair("First Name", employeeDataFields[0].getText());
    Pair secondPair = new Pair("Last Name", employeeDataFields[1].getText());
    Pair thirdPair = new Pair("Salary", employeeDataFields[2].getText());
    Pair fourthPair = new Pair("Department ID", employeeDataFields[3].getText());

    LinkedList<Pair> employeeData = new LinkedList<>();
    employeeData.add(firstPair);
    employeeData.add(secondPair);
    employeeData.add(thirdPair);
    employeeData.add(fourthPair);
    return employeeData;
  }

  public void closePopup() {
    popupStage.close();
  }
}

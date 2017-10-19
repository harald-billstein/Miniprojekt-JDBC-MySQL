package view;

import java.lang.reflect.Array;
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
  private Label firstNameLabel, lastNameLabel, salaryLabel, departmentIdLabel, errorLabel;
//  private TextField firstNameInput, lastNameInput, salaryInput, departmentIdInput;
  private TextField[] employeeDataFields = new TextField[4];
  private String[] labelStrings = {"First name:", "Last name:", "Salary:", "Department ID:"};
  private Label[] labels = new Label[4];
  private VBox mainBox;
  private HBox firstNameBox, lastNameBox, salaryBox, departmentIdBox, buttonBox;
  private final static int popupWidth = 600;
  private final static int popupHeight = 200;

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
    mainBox.setPrefSize(popupWidth, popupHeight);
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
    }
/*
    firstNameLabel = new Label("First name:");
    lastNameLabel = new Label("Last name:");
    salaryLabel = new Label("Salary:");
    departmentIdLabel = new Label("Department ID:");
    firstNameInput = new TextField("Name");
    lastNameInput = new TextField("Last name");
    salaryInput = new TextField("Salary");
    departmentIdInput = new TextField("Department ID");
*/
    firstNameBox = new HBox(labels[0], employeeDataFields[0]);
    lastNameBox = new HBox(labels[1], employeeDataFields[1]);
    salaryBox = new HBox(labels[2], employeeDataFields[2]);
    departmentIdBox = new HBox(labels[3], employeeDataFields[3]);
    buttonBox = new HBox(getConfirmButton(), getCancelButton());
    errorLabel = new Label();
  }

  private void setupPopupLayout() {
    mainBox.getChildren().addAll(firstNameBox, lastNameBox, salaryBox,
        departmentIdBox, errorLabel, buttonBox);
    departmentIdBox.setPadding(new Insets(0, 0, 20, 0));
    firstNameBox.setAlignment(Pos.CENTER);
    lastNameBox.setAlignment(Pos.CENTER);
    salaryBox.setAlignment(Pos.CENTER);
    departmentIdBox.setAlignment(Pos.CENTER);
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

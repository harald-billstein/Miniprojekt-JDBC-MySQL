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
  private VBox mainBox;
  private HBox buttonBox;
  private TextField firstNameField;
  private TextField lastNameField;
  private TextField salaryField;
  private TextField departmentField;
  private final static int PREFERRED_POPUP_WIDTH = 600;
  private final static int PREFERRED_POPUP_HEIGHT = 200;

  public EditEmployeePopup(Stage primaryStage, Observers observers) {
    super("UpdateEmployeeCancelButton", "Update Employee", "PopupEditEmployeeConfirmButton",
        observers);
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


    createFirstNameBox();
    createLastNameBox();
    createSalaryBox();



    buttonBox = new HBox(getConfirmButton(), getCancelButton());
    errorLabel = new Label();
  }

  private void createLastNameBox() {
    lastNameField = new TextField();
    lastNameField.setId("Lastname");
    HBox lastNameBox = new HBox(new Label("Last name: "), lastNameField);
    lastNameBox.setAlignment(Pos.CENTER);
    mainBox.getChildren().add(lastNameBox);

  }



  private void createSalaryBox() {
    salaryField = new TextField();
    salaryField.setId("Salary");
    HBox salaryFieldBox = new HBox(new Label("Salary: "), salaryField);
    salaryFieldBox.setAlignment(Pos.CENTER);
    mainBox.getChildren().add(salaryFieldBox);

  }

  private void createFirstNameBox() {
    firstNameField = new TextField();
    firstNameField.setId("Firstname");
    HBox firstNameBox = new HBox(new Label("First name: "), firstNameField);
    firstNameBox.setAlignment(Pos.CENTER);
    mainBox.getChildren().add(firstNameBox);
  }

  private void setupPopupLayout() {
    mainBox.getChildren().addAll(errorLabel, buttonBox);
    buttonBox.setAlignment(Pos.CENTER);
  }

  public TextField[] getEmployeeDataArray() {
    return employeeDataFields;
  }

  public String getFirstName() {
    return firstNameField.getText();
  }

  public String getLastName() {
    return lastNameField.getText();
  }

  public String getSalary() {
    return salaryField.getText();
  }

  public void setFirstName(String fname) {
    firstNameField.setText(fname);
  }

  public void setLastName(String lname) {
    lastNameField.setText(lname);
  }

  public void setSalary(int salary) {
    salaryField.setText("" + salary);
  }


  public String getDepartment() {
    return departmentField.getText();
  }



  public void setErrorLabelText(String errorMessage) {
    errorLabel.setText(errorMessage);
  }

  public void closePopup() {
    popupStage.close();
  }
}

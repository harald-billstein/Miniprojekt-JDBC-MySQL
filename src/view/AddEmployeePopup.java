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

public class AddEmployeePopup extends AbstractPopup {

  private Stage popupStage;
  private Scene scene;
  private Label errorLabel;
  private TextField[] employeeDataFields;
  private VBox mainBox;
  private HBox buttonBox;
  private TextField firstNameField;
  private TextField lastNameField;
  private TextField salaryField;
  private TextField departmentField;
  private final static int NUMBER_OF_INPUT_FIELDS = 4;
  private final static int PREFERRED_POPUP_WIDTH = 600;
  private final static int PREFERRED_POPUP_HEIGHT = 200;

  public AddEmployeePopup(Stage primaryStage, Observers observers) {
    super("AddEmployeeCancelButton", "Add Employee",
        "PopupAddEmployeeConfirmButton", observers);
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
    employeeDataFields = new TextField[NUMBER_OF_INPUT_FIELDS];
    
    mainBox = new VBox();
    scene = new Scene(mainBox);
    
    createFirstNameBox();
    createLastNameBox();
    createSalaryBox();
    createDepartmentBox();
    
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

  private void createDepartmentBox() {
    departmentField = new TextField();
    departmentField.setId("DepartmentID");
    HBox departmentBox = new HBox(new Label("Department ID: "), departmentField);
    departmentBox.setAlignment(Pos.CENTER);
    mainBox.getChildren().add(departmentBox);
    
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

  public String getDepartment() {
    return departmentField.getText();
  }

  public void setErrorLabelText(String errorText) {
    errorLabel.setText(errorText);
  }
  
  public void closePopup() {
    popupStage.close();
  }
}

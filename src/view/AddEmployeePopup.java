package view;

import controller.TheFirmController.Observers;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Creates a popup which is used to add a new employee to the database
 * @author Cristoffer
 * @author Harald
 *
 */

public class AddEmployeePopup extends AbstractPopup {

  private Stage popupStage;
  private Scene scene;
  private Label errorLabel;
  private VBox mainBox;
  private HBox buttonBox;
  private TextField firstNameField;
  private TextField lastNameField;
  private TextField salaryField;
  private TextField departmentField;
  private final static int PREFERRED_POPUP_WIDTH = 600;
  private final static int PREFERRED_POPUP_HEIGHT = 200;

  /**
   * Creates a new stage and assigns the primary stage
   *
   * @param primaryStage The primary stage which this popup belongs to
   * @param observers Inner class Observers in Controller to use with this class
   */
  public AddEmployeePopup(Stage primaryStage, Observers observers) {
    super("Add Employee", "PopupAddEmployeeConfirmButton", observers);
    popupStage = new Stage();
    popupStage.initOwner(primaryStage);
  }

  /**
   * Calls methods to create popup resources, set layout, set size of popup window.
   * Sets modality of window.
   * Shows the popup.
   * Sets lambda expression for the cancel button.
   */
  public void createAddEmployeePopup() {
    createPopupResources();
    getCancelButton().setOnAction((event -> popupStage.close()));
    setupPopupLayout();
    mainBox.setPrefSize(PREFERRED_POPUP_WIDTH, PREFERRED_POPUP_HEIGHT);
    popupStage.setScene(scene);
    popupStage.initModality(Modality.APPLICATION_MODAL);
    popupStage.show();
  }

  /**
   * Creates popup resources.
   */
  private void createPopupResources() {
    mainBox = new VBox();
    scene = new Scene(mainBox);
    createFirstNameBox();
    createLastNameBox();
    createSalaryBox();
    createDepartmentBox();
    buttonBox = new HBox(getConfirmButton(), getCancelButton());
    errorLabel = new Label();
  }

  /**
   * Creates TextField, Label and HBox for last name field.
   * Adds the elements to main VBox
   */
  private void createLastNameBox() {
    lastNameField = new TextField();
    lastNameField.setId("Lastname");
    HBox lastNameBox = new HBox(new Label("Last name: "), lastNameField);
    lastNameBox.setAlignment(Pos.CENTER);
    mainBox.getChildren().add(lastNameBox);

  }

  /**
   * Creates TextField, Label and HBox for department field.
   * Adds the elements to main VBox
   */
  private void createDepartmentBox() {
    departmentField = new TextField();
    departmentField.setId("DepartmentID");
    HBox departmentBox = new HBox(new Label("Department ID: "), departmentField);
    departmentBox.setAlignment(Pos.CENTER);
    mainBox.getChildren().add(departmentBox);

  }

  /**
   * Creates TextField, Label and HBox for salary field.
   * Adds the elements to main VBox
   */
  private void createSalaryBox() {
    salaryField = new TextField();
    salaryField.setId("Salary");
    HBox salaryFieldBox = new HBox(new Label("Salary: "), salaryField);
    salaryFieldBox.setAlignment(Pos.CENTER);
    mainBox.getChildren().add(salaryFieldBox);

  }

  /**
   * Creates TextField, Label and HBox for first name field.
   * Adds the elements to main VBox
   */
  private void createFirstNameBox() {
    firstNameField = new TextField();
    firstNameField.setId("Firstname");
    HBox firstNameBox = new HBox(new Label("First name: "), firstNameField);
    firstNameBox.setAlignment(Pos.CENTER);
    mainBox.getChildren().add(firstNameBox);
  }

  /**
   * Adds elements to main VBox.
   * Sets alignment for elements.
   */
  private void setupPopupLayout() {
    mainBox.getChildren().addAll(errorLabel, buttonBox);
    buttonBox.setAlignment(Pos.CENTER);
  }

  /**
   * Getter for first name
   * @return The first name of the Employee
   */
  public String getFirstName() {
    return firstNameField.getText();
  }

  /**
   * Getter for last name
   * @return The last name of the Employee
   */
  public String getLastName() {
    return lastNameField.getText();
  }

  /**
   * Getter for salary name
   * @return The salary of the Employee
   */
  public String getSalary() {
    return salaryField.getText();
  }

  /**
   * Getter for department
   * @return The department of the Employee
   */
  public String getDepartment() {
    return departmentField.getText();
  }

  /**
   * Sets the error text to be displayed if and input is invalid
   * @param errorMessage Error text to be displayed in the popup
   */
  public void setErrorLabelText(String errorMessage) {
    errorLabel.setText(errorMessage);
  }

  /**
   * Closes the popup
   */
  public void closePopup() {
    popupStage.close();
  }
}

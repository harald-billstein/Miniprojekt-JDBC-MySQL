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

/**
 * Creates a popup which is used to search for an employee
 *
 * @author Cristoffer
 * @author Harald
 */

public class SearchEmployeePopup extends AbstractPopup {

  private Stage popupStage;
  private Scene scene;
  private Label employeeNameLabel, errorLabel;
  private TextField employeeNameInput;
  private HBox topBox, middleBox, bottomBox;
  private VBox mainBox;
  private final static int PREFERRED_POPUP_WIDTH = 600;
  private final static int PREFERRED_POPUP_HEIGHT = 200;

  /**
   * Creates a popup which is used to search for an Employee
   *
   * @param primaryStage The primary stage which this popup belongs to
   * @param observers Observers to use with this class
   */
  public SearchEmployeePopup(Stage primaryStage, Observers observers) {
    super("Search", "PopupSearchEmployeeConfirmButton", observers);
    popupStage = new Stage();
    popupStage.initOwner(primaryStage);
  }

  /**
   * Calls methods to create popup resources, set layout, set size of popup window.
   * Sets modality of window.
   * Shows the popup.
   * Sets lambda expression for the cancel button.
   */
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

  /**
   * Creates popup resources.
   */
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

  /**
   * Closes the popup
   */

  public void closePopup() {
    popupStage.close();
  }

  /**
   * Getter for Employee name TextField
   *
   * @return Employee to search for
   */
  public String getEmployeeNameInput() {
    return employeeNameInput.getText();
  }

  /**
   * Sets the error text to be displayed if and input is invalid
   *
   * @param errorMessage Error text to be displayed in the popup
   */
  public void setErrorLabelText(String errorMessage) {
    errorLabel.setText(errorMessage);
  }
}

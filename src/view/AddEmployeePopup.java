package view;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddEmployeePopup extends AbstractPopup {

  private Stage popupStage;
  private Scene scene;
  private Button addButton, cancelButton;
  private Label firstName, lastName, salary, departmentId;
  private TextField firstNameInput, lastNameInput, salaryInput, departmentIdInput;
  private VBox topBox;
  private HBox bottomBox;
  private final static int popupWidth = 600;
  private final static int popupHeight = 200;

  public AddEmployeePopup(Stage primaryStage, EventHandler<ActionEvent> eventHandler) {
    super("AddEmployeeCancelButton", "Add Employee",
        "AddEmployeeConfirmButton", eventHandler);
    popupStage = new Stage();
    popupStage.initOwner(primaryStage);
  }

  public void createAddEmployeePopup() {
//    createPopupResources();
//    createCancelButton();
//    createConfirmButton();
//    setupPopupLayout();
//    setButtonLook();
//    setupTopBox();
//    setupMiddleBox();
//    setupBottomBox();
//    setPrefSize(popupWidth, popupHeight);
//    popupStage.setScene(scene);
//    popupStage.initModality(Modality.APPLICATION_MODAL);
//    popupStage.show();
  }

  @Override
  public void createConfirmButton() {

  }

  @Override
  public void setObserver(EventHandler<ActionEvent> eventHandler) {

  }

  @Override
  public void closePopup() {

  }
}

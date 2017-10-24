package view;

import controller.TheFirmController.Observers;
import javafx.scene.control.Button;

abstract class AbstractPopup implements PopupInterface {

  private Button cancelButton, confirmButton;
  private String confirmButtonText, cancelButtonId, confirmButtonId;
  private Observers observers;
  private final static int PREFERRED_BUTTON_WIDTH = 100;

  AbstractPopup(String cancelButtonId, String confirmButtonText,
      String confirmButtonId, Observers observers) {
    this.confirmButtonText = confirmButtonText;
    this.cancelButtonId = cancelButtonId;
    this.confirmButtonId = confirmButtonId;
    this.observers = observers;
    createCancelButton();
    createConfirmButton();
  }

  public void createConfirmButton() {
    confirmButton = new Button(confirmButtonText);
    confirmButton.setMinWidth(PREFERRED_BUTTON_WIDTH);
    confirmButton.setId(confirmButtonId);
    confirmButton.setOnAction(observers.getActionEvent());
  }

  public void createCancelButton() {
    cancelButton = new Button("Cancel");
    cancelButton.setMinWidth(PREFERRED_BUTTON_WIDTH);
    //TODO: REMOVE? MAYBE NOT USED
    cancelButton.setId(cancelButtonId);
  }

  Button getCancelButton() {
    return cancelButton;
  }

  Button getConfirmButton() {
    return confirmButton;
  }

  public void setObserver(Observers observers) {
    this.observers = observers;
  }
}

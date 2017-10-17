package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

abstract class AbstractPopup implements PopupInterface {

  private Button cancelButton, confirmButton;
  private String confirmButtonText, cancelButtonId, confirmButtonId;
  private EventHandler<ActionEvent> eventHandler;
  private final static int PREFERRED_BUTTON_WIDTH = 100;

  AbstractPopup(String cancelButtonId, String confirmButtonText,
      String confirmButtonId, EventHandler<ActionEvent> eventHandler) {
    this.confirmButtonText = confirmButtonText;
    this.cancelButtonId = cancelButtonId;
    this.confirmButtonId = confirmButtonId;
    this.eventHandler = eventHandler;
    createCancelButton();
    createConfirmButton();
  }

  public void createConfirmButton() {
    confirmButton = new Button(confirmButtonText);
    confirmButton.setMinWidth(PREFERRED_BUTTON_WIDTH);
    confirmButton.setId(confirmButtonId);
    confirmButton.setOnAction(eventHandler);
  }

  public void createCancelButton() {
    cancelButton = new Button("Cancel");
    cancelButton.setMinWidth(PREFERRED_BUTTON_WIDTH);
    cancelButton.setId(cancelButtonId);
  }

  Button getCancelButton() {
    return cancelButton;
  }

  Button getConfirmButton() {
    return confirmButton;
  }

  public void setObserver(EventHandler<ActionEvent> eventHandler) {
    this.eventHandler = eventHandler;
  }
}

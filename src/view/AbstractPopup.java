package view;

import controller.TheFirmController.Observers;
import javafx.scene.control.Button;

/**
 * This abstract class creates a confirmbutton, a cancelbutton and sets observers.
 * @author Cristoffer
 * @author Harald
 */

abstract class AbstractPopup implements PopupInterface {

  private Button cancelButton, confirmButton;
  private String confirmButtonText, confirmButtonId;
  private Observers observers;
  private final static int PREFERRED_BUTTON_WIDTH = 100;

  /**
   * @param confirmButtonText Text to show on confirm-button
   * @param confirmButtonId ID to set on the confirm-button
   * @param observers Inner class Observers in Controller to use with this class
   */

  AbstractPopup(String confirmButtonText,
      String confirmButtonId, Observers observers) {
    this.confirmButtonText = confirmButtonText;
    this.confirmButtonId = confirmButtonId;
    this.observers = observers;
    createCancelButton();
    createConfirmButton();
  }

  /**
   * Creates a confirm button with text and id specified in the constructor.
   * Sets width of button
   * Sets which type of event used by the button
   */

  public void createConfirmButton() {
    confirmButton = new Button(confirmButtonText);
    confirmButton.setMinWidth(PREFERRED_BUTTON_WIDTH);
    confirmButton.setId(confirmButtonId);
    confirmButton.setOnAction(observers.getActionEvent());
  }

  /**
   * Creates a cancel button.
   * Sets width of button
   */
  public void createCancelButton() {
    cancelButton = new Button("Cancel");
    cancelButton.setMinWidth(PREFERRED_BUTTON_WIDTH);
  }

  /**
   * Getter for the cancel button.
   * @return This cancel button object
   */
  Button getCancelButton() {
    return cancelButton;
  }

  /**
   * Getter for the confirm button.
   * @return This confirm button object
   */
  Button getConfirmButton() {
    return confirmButton;
  }

  /**
   * Sets the observers for this class.
   * @param observers Inner class of observers in TheFirmController
   */
  public void setObserver(Observers observers) {
    this.observers = observers;
  }
}

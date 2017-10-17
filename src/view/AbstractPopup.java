package view;

import javafx.scene.control.Button;

public abstract class AbstractPopup {
  private Button cancelButton;
  private String cancelButtonText, cancelButtonId;

  public AbstractPopup(String cancelButtonText, String cancelButtonId) {
    this.cancelButtonText = cancelButtonText;
    this.cancelButtonId = cancelButtonId;
    createCancelButton();
  }

  public void createCancelButton() {
    cancelButton = new Button(cancelButtonText);
    cancelButton.setId(cancelButtonId);
  }

  public Button getCancelButton() {
    return cancelButton;
  }
}

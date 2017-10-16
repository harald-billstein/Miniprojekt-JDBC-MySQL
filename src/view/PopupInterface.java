package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public interface PopupInterface {
  void createConfirmButton();
  void createAbortButton();
  void setObserver(EventHandler<ActionEvent> eventHandler);
  void closePopup();
}

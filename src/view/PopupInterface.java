package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public interface PopupInterface {
  void createConfirmButton();
  void createCancelButton();
  void setObserver(EventHandler<ActionEvent> eventHandler);
  void closePopup();
}

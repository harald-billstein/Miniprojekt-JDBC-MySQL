package view;

import controller.TheFirmController.Observers;

public interface PopupInterface {
  void createConfirmButton();
  void createCancelButton();
  void setObserver(Observers observers);
  void closePopup();
}

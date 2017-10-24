package view;

import controller.TheFirmController.Observers;

/**
 * Interface to use with the AbstractPopup
 */

public interface PopupInterface {
  void createConfirmButton();
  void createCancelButton();
  void setObserver(Observers observers);
  void closePopup();
}

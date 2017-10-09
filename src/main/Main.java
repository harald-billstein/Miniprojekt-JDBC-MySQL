package main;
import java.util.logging.Level;

import controller.TheFirmController;
import view.ApplicationGUI;

class Main {

  public static void main(String[] args) {
    // TURN OFF HIBERNATE LOGGER
    java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
    //TheFirm theFirm = new TheFirm();
    //theFirm.start();
    TheFirmController theFirmController = new TheFirmController();
    theFirmController.start();
    


	
	  

    
  }
}
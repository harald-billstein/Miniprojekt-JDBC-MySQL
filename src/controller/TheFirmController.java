package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import model.CompanyCar;
import model.Department;
import model.Employee;
import view.ApplicationGUI;
import view.PopupSearchEmployee;

public class TheFirmController implements EventHandler<ActionEvent> {

  private HibernateSessionManager hibernateSessionManager;
  private ApplicationGUI applicationGUI;
  private EmployeeIO employeeIO;
  private CompanyCarIO companyCarIO;
  private DepartmentIO departmentIO;
  private final ObservableList<Employee> data = FXCollections.observableArrayList();
  private PopupSearchEmployee popupSearchEmployee;

  public TheFirmController() {
  }

  public void start() {
    kickstartControllerresources();
  }

  private void kickstartControllerresources() {

    List<Class<?>> clazzes = new ArrayList<Class<?>>();
    clazzes.add(Employee.class);
    clazzes.add(Department.class);
    clazzes.add(CompanyCar.class);

    try {
      hibernateSessionManager = new HibernateSessionManager(clazzes);
    } catch (Exception e) {

    }
    employeeIO = new EmployeeIO(hibernateSessionManager);
    companyCarIO = new CompanyCarIO(hibernateSessionManager);
    departmentIO = new DepartmentIO(hibernateSessionManager);
  }


  @Override
  public void handle(ActionEvent event) {
    Button button = (Button) event.getSource();

    switch (button.getId()) {
      case "MainMenuSearchButton": createSearchEmployeePopup();
        break;
      case "MainMenuAddButton":
        break;
      case "MainMenuRemoveButton":
        break;
      case "MainMenuEditButton":
        break;
      case "SearchPopupConfirmButton": List<Employee> employees =
          employeeIO.seachEmployeeName(popupSearchEmployee.getEmployeeNameInput());
          applicationGUI.getCenterTable().setItems(getSeachedEmployees(employees));
        break;
    }

//    if (button.getText().equals("Search")) {
//
//      createSearchEmployeePopup();
//
//				System.out.println(button.getText());
//				List<Employee> employees = employeeIO.seachEmployeeName("we");
//				applicationGUI.getCenterTable().setItems(getSeachedEmployees(employees));
//
//    } else if (button.getText().equals("Add")) {
//      System.out.println(button.getText());
//    } else if (button.getText().equals("Remove")) {
//      System.out.println(button.getText());
//    } else if (button.getText().equals("Edit")) {
//      System.out.println(button.getText());
//    }
//    else if (button.getId().equals("PopupSearchButton")) {
//      System.out.println("PopupSearchButton pressed");
//    }
  }

  public void setGui(ApplicationGUI applicationGUI) {
    this.applicationGUI = applicationGUI;
  }

  public ObservableList<Employee> getEmployees() {
    data.clear();
    List<Employee> employees = employeeIO.read();

    for (Employee employee : employees) {
      data.add(employee);
    }
    return data;
  }

  public ObservableList<Employee> getSeachedEmployees(List<Employee> employees) {
    data.clear();

    for (Employee employee : employees) {
      data.add(employee);
    }
    return data;
  }

  public void createSearchEmployeePopup() {
    popupSearchEmployee = new PopupSearchEmployee(applicationGUI.getPrimaryStage());
    popupSearchEmployee.setObserver(this);
    popupSearchEmployee.createPopupSearchEmployeeWindow();
  }

}
 
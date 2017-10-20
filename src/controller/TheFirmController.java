package controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;
import model.CompanyCar;
import model.Department;
import model.Employee;
import model.EmployeeBuilder;
import model.EmployeeObservable;
import model.ToObservableList;
import view.AddEmployeePopup;
import view.ApplicationGUI;
import view.SearchEmployeePopup;
import view.WebPagePresenter;

public class TheFirmController {

  private HibernateSessionManager hibernateSessionManager;
  private ApplicationGUI applicationGUI;
  private EmployeeIO employeeIO;
  private CompanyCarIO companyCarIO;
  private DepartmentIO departmentIO;
  private ToObservableList toObservableList;
  private ObservableList<EmployeeObservable> data;
  private SearchEmployeePopup searchEmployeePopup;
  private AddEmployeePopup addEmployeePopup;
  private Observers observers;
  private EmployeeObservable selectedEmployee;
  private Queue<Employee> employeeQueue;

  public Observers getObservers() {
    return observers;
  }

  public TheFirmController() {
  }

  public void start() {
    kickstartControllerresources();
  }

  private void kickstartControllerresources() {

    List<Class<?>> clazzes = new ArrayList<>();
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

    data = FXCollections.observableArrayList();
    toObservableList = new ToObservableList();
    observers = new Observers();
    employeeQueue = new LinkedList<>();
  }

  private void removeEmployee() {

    Boolean success = employeeIO.delete(selectedEmployee.getEmployeeId());

    if (success) {
      System.out.println("successs!");
      applicationGUI.getCenterTable().setItems(getEmployees());
    }

    // TODO Error message in a label in main GUI?
  }

  public void setGui(ApplicationGUI applicationGUI) {
    this.applicationGUI = applicationGUI;
  }

  public ObservableList<EmployeeObservable> getEmployees() {
    data.clear();
    data = toObservableList.convertList(employeeIO.read());
    return data;
  }

  public ObservableList<EmployeeObservable> getSeachedEmployees(List<Employee> employees) {
    data.clear();
    data = toObservableList.convertList(employees);
    return data;
  }

  private void createSearchEmployeePopup() {
    searchEmployeePopup = new SearchEmployeePopup(applicationGUI.getPrimaryStage(), observers);
    searchEmployeePopup.createSearchEmployeePopupWindow();
  }

  private void createAddEmployeePopup() {
    addEmployeePopup = new AddEmployeePopup(applicationGUI.getPrimaryStage(), observers);
    addEmployeePopup.createAddEmployeePopup();
  }

  private void doEmployeeSearch() {
    if (searchEmployeePopup.getEmployeeNameInput().length() > 0) {
      List<Employee> employees =
          employeeIO.seachEmployeeName(searchEmployeePopup.getEmployeeNameInput());
      applicationGUI.getCenterTable().setItems(getSeachedEmployees(employees));
      searchEmployeePopup.closePopup();
    } else {
      searchEmployeePopup.getErrorLabel().setText("Error. Empty search string");
    }
  }

  private void addNewEmployee() {

//TODO: Bryt ut all kod.
    boolean addEmployee = true;
    for (int i = 0; i < addEmployeePopup.getEmployeeDataArray().length; i++) {
      String employeeInput = addEmployeePopup.getEmployeeDataArray()[i].getText().trim();
      if (employeeInput.isEmpty()) {
        addEmployeePopup.getErrorLabel().setText("Error. One or more inputs are empty.");
        addEmployee = false;
      }
      if (addEmployeePopup.getEmployeeDataArray()[i].getId().equals("Field2") && addEmployee) {
        try {
          int parsedInput = Integer.parseInt(employeeInput);
          if (parsedInput <= 0) {
            addEmployeePopup.getErrorLabel().setText("Error. Salary must be larger than 0");
            addEmployee = false;
          }
        } catch (Exception e) {
          addEmployee = false;
          addEmployeePopup.getErrorLabel().setText("Error. Salary must be a number");
        }
      }
      if (addEmployeePopup.getEmployeeDataArray()[i].getId().equals("Field3") && addEmployee) {
        try {
          int parsedInput = Integer.parseInt(employeeInput);
          List<Department> numberOfDepartments = departmentIO.read();
          if (!(parsedInput > 0 && parsedInput <= numberOfDepartments.size())) {
            addEmployeePopup.getErrorLabel().setText("Invalid input for Department ID");
            addEmployee = false;
          }
        } catch (Exception e) {
          addEmployee = false;
          addEmployeePopup.getErrorLabel().setText("Department ID must be a number");
        }
      }
    }

    if (addEmployee) {
//      LinkedList<Pair> employeeData = addEmployeePopup.getEmployeeData();
      TextField[] employeeData = addEmployeePopup.getEmployeeDataArray();

      Employee employee = new EmployeeBuilder()
          .setFirstName(employeeData[0].getText())
          .setLastName(employeeData[1].getText())
          .setSalary(Integer.parseInt(employeeData[2].getText()))
          .setDepartmentId(Integer.parseInt(employeeData[3].getText()))
          .build();
      employeeQueue.add(employee);

      while (!employeeQueue.isEmpty()) {
        employeeIO.create(employeeQueue.poll());
      }
      addEmployeePopup.closePopup();
      applicationGUI.getCenterTable().setItems(getEmployees());
    }
  }

  public class Observers {

    private EventHandler<MouseEvent> mouseEvent = new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        Object object = event.getSource();

        TableView<EmployeeObservable> tableView = (TableView<EmployeeObservable>) object;
        selectedEmployee = tableView.getSelectionModel().getSelectedItem();
      }
    };

    private EventHandler<WindowEvent> windowEvent = new EventHandler<WindowEvent>() {

      @Override
      public void handle(WindowEvent event) {
        System.out.println("Exiting App");
        Platform.exit();
        System.exit(0);

      }
    };

    private EventHandler<ActionEvent> actionEvent = new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {

        Object object = event.getSource();

        if (object instanceof Button) {
          Button button = (Button) object;

          switch (button.getId()) {
            case "MainMenuSearchButton":
              createSearchEmployeePopup();
              break;
            case "MainMenuAddButton":
              createAddEmployeePopup();
              break;
            case "MainMenuRemoveButton":
              removeEmployee();
              break;
            case "MainMenuEditButton":
              break;
            case "PopupSearchEmployeeConfirmButton":
              doEmployeeSearch();
              break;
            case "AddEmployeeConfirmButton":
              addNewEmployee();
              break;
          }
        } else if (object instanceof MenuItem) {
          WebPagePresenter webPagePresenter = new WebPagePresenter(
              applicationGUI.getPrimaryStage());
          webPagePresenter.showGitPage();
        }
      }
    };

    public EventHandler<MouseEvent> getMouseEvent() {
      return mouseEvent;
    }

    public EventHandler<WindowEvent> getWindowEvent() {
      return windowEvent;
    }

    public EventHandler<ActionEvent> getActionEvent() {
      return actionEvent;
    }
  }
}

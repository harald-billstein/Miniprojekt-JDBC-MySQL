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
import view.EditEmployeePopup;
import view.SearchEmployeePopup;
import view.WebPagePresenter;

public class TheFirmController {

  private HibernateSessionManager hibernateSessionManager;
  private ApplicationGUI applicationGUI;
  private EmployeeIO employeeIO;
  private DepartmentIO departmentIO;
  private ToObservableList toObservableList;
  private ObservableList<EmployeeObservable> data;
  private SearchEmployeePopup searchEmployeePopup;
  private AddEmployeePopup addEmployeePopup;
  private EditEmployeePopup editEmployeePopup;
  private Observers observers;
  private EmployeeObservable selectedEmployee;
  private Queue<Employee> employeeQueue;
  private UserInputChecker userInputChecker;
  private int MINIMUM_SALARY = 19000;

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
      // TODO If database is down!
    }
    employeeIO = new EmployeeIO(hibernateSessionManager);
    departmentIO = new DepartmentIO(hibernateSessionManager);

    data = FXCollections.observableArrayList();
    toObservableList = new ToObservableList();
    observers = new Observers();
    employeeQueue = new LinkedList<>();
    userInputChecker = new UserInputChecker(MINIMUM_SALARY, departmentIO.read().size());

  }

  private void removeEmployee() {

    if (selectedEmployee != null) {
      Boolean success = employeeIO.delete(selectedEmployee.getEmployeeId());

      if (success) {
        applicationGUI.getCenterTable().setItems(getEmployees());
      }
    }
    // TODO If no user was selected, print out error in GUI?
  }

  private void createSearchEmployeePopup() {
    searchEmployeePopup = new SearchEmployeePopup(applicationGUI.getPrimaryStage(), observers);
    searchEmployeePopup.createSearchEmployeePopupWindow();
  }

  private void createAddEmployeePopup() {
    addEmployeePopup = new AddEmployeePopup(applicationGUI.getPrimaryStage(), observers);
    addEmployeePopup.createAddEmployeePopup();
  }

  private void createEditEmployeePopup() {
    editEmployeePopup = new EditEmployeePopup(applicationGUI.getPrimaryStage(), observers);
    if (selectedEmployee != null) {
      editEmployeePopup.createEditEmployeePopup();
      editEmployeePopup.setFirstName(selectedEmployee.getFirstName());
      editEmployeePopup.setLastName(selectedEmployee.getLastName());
      editEmployeePopup.setSalary(selectedEmployee.getSalary());

    }
    // TODO If no user was selected, print out error in GUI?
  }

  private void doEmployeeSearch() {
    boolean success = userInputChecker.isValidFirstName(searchEmployeePopup.getEmployeeNameInput());
    if (success) {
      List<Employee> employees =
          employeeIO.seachEmployeeName(searchEmployeePopup.getEmployeeNameInput());
      applicationGUI.getCenterTable().setItems(getSeachedEmployees(employees));
      searchEmployeePopup.closePopup();
    } else {
      searchEmployeePopup.setErrorLabelText(userInputChecker.getUserInputErrorText().getErrors().get("search"));
    }
  }

  private void buildEmployee() {
    boolean inputValid = true;

    String firstName = addEmployeePopup.getFirstName().trim();
    inputValid = userInputChecker.isValidFirstName(firstName);
    if (!inputValid) {
      addEmployeePopup
          .setErrorLabelText(userInputChecker.getUserInputErrorText().getErrors().get("firstName"));
      return;
    }

    String lastName = addEmployeePopup.getLastName().trim();
    inputValid = userInputChecker.isValidLastName(lastName);
    if (!inputValid) {
      addEmployeePopup
          .setErrorLabelText(userInputChecker.getUserInputErrorText().getErrors().get("lastName"));
      return;
    }

    String salary = addEmployeePopup.getSalary();
    inputValid = userInputChecker.isValidSalary(salary);
    if (!inputValid) {
      addEmployeePopup
          .setErrorLabelText(userInputChecker.getUserInputErrorText().getErrors().get("salary"));
      return;
    }

    String departmentId = addEmployeePopup.getDepartment();
    inputValid = userInputChecker.isValidDepartmentId(departmentId);
    if (!inputValid) {
      addEmployeePopup.setErrorLabelText(
          userInputChecker.getUserInputErrorText().getErrors().get("department"));
      return;
    }

    if (inputValid) {
      Employee employee = new EmployeeBuilder().setFirstName(firstName).setLastName(lastName)
          .setSalary(Integer.parseInt(salary)).setDepartmentId(Integer.parseInt(departmentId))
          .build();

      employeeQueue.add(employee);
      writeEmployeeQueueToDatabase(employeeQueue);
      updateEmployeeTable();
      addEmployeePopup.closePopup();
    }

  }

  private void updateEmployeeTable() {
    applicationGUI.getCenterTable().setItems(getEmployees());
  }

  private void writeEmployeeQueueToDatabase(Queue<Employee> employeeQueue) {
    while (!employeeQueue.isEmpty()) {
      employeeIO.create(employeeQueue.poll());
    }

  }

  private void updateEmployee() {
    boolean inputValid = true;

    String firstName = editEmployeePopup.getFirstName().trim();
    inputValid = userInputChecker.isValidFirstName(firstName);
    if (!inputValid) {
      editEmployeePopup
          .setErrorLabelText(userInputChecker.getUserInputErrorText().getErrors().get("firstName"));
      return;
    }

    String lastName = editEmployeePopup.getLastName().trim();
    inputValid = userInputChecker.isValidLastName(lastName);
    if (!inputValid) {
      editEmployeePopup
          .setErrorLabelText(userInputChecker.getUserInputErrorText().getErrors().get("lastName"));
      return;
    }

    String salary = editEmployeePopup.getSalary();
    inputValid = userInputChecker.isValidSalary(salary);
    if (!inputValid) {
      editEmployeePopup
          .setErrorLabelText(userInputChecker.getUserInputErrorText().getErrors().get("salary"));
      return;
    }

    if (inputValid) {
      Employee employee = new Employee();
      employee.setEmployeeId(selectedEmployee.getEmployeeId());
      employee.setFirstName(firstName);
      employee.setLastName(lastName);
      employee.setSalary(Integer.parseInt(salary));

      employeeIO.updateEmployee(employee);
      updateEmployeeTable();
      editEmployeePopup.closePopup();
      selectedEmployee = null;
    }



  }

  public void setGui(ApplicationGUI applicationGUI) {
    this.applicationGUI = applicationGUI;
  }

  // TODO similar to getSeachedEmployees
  public ObservableList<EmployeeObservable> getEmployees() {
    data.clear();
    data = toObservableList.convertList(employeeIO.read());
    return data;
  }

  // TODO similar to getEmployess
  public ObservableList<EmployeeObservable> getSeachedEmployees(List<Employee> employees) {
    data.clear();
    data = toObservableList.convertList(employees);
    return data;
  }

  public Observers getObservers() {
    return observers;
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
              createEditEmployeePopup();
              break;
            case "MainMenuResetButton":
              applicationGUI.getCenterTable().setItems(getEmployees());
              break;
            case "PopupSearchEmployeeConfirmButton":
              doEmployeeSearch();
              break;
            case "PopupAddEmployeeConfirmButton":
              buildEmployee();
              break;
            case "PopupEditEmployeeConfirmButton":
              updateEmployee();
              break;
          }
        } else if (object instanceof MenuItem) {
          WebPagePresenter webPagePresenter =
              new WebPagePresenter(applicationGUI.getPrimaryStage());
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

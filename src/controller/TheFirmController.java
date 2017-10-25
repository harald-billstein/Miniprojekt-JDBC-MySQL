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
import model.ErrorMessages;
import model.ToObservableList;
import view.AddEmployeePopup;
import view.ApplicationGUI;
import view.EditEmployeePopup;
import view.SearchEmployeePopup;
import view.WebPagePresenter;

/**
 * Class controlling the flow of data between the database and the user interface.
 *
 * @author Harald & Cristoffer
 */
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
  private ErrorMessages userInputErrorText;
  private int MINIMUM_SALARY = 19000;


  /**
   * Start method.
   */
  public void start() {
    kickstartControllerresources();
  }

  /**
   * Starts recourses needed.
   */
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
    userInputErrorText = new ErrorMessages(MINIMUM_SALARY);
    userInputChecker = new UserInputChecker(MINIMUM_SALARY, departmentIO.read().size());

  }

  /**
   * Removes selected employee from database.
   */
  private void removeEmployee() {

    if (selectedEmployee != null) {
      Boolean success = employeeIO.delete(selectedEmployee.getEmployeeId());

      if (success) {
        applicationGUI.getCenterTable().setItems(getEmployees());
      }
    }
    selectedEmployee = null;
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

  /**
   * Search the database for employees.
   */
  private void doEmployeeSearch() {
    boolean success = userInputChecker.isFirstNameValid(searchEmployeePopup.getEmployeeNameInput());
    if (success) {
      List<Employee> employees =
          employeeIO.seachEmployeeName(searchEmployeePopup.getEmployeeNameInput());
      applicationGUI.getCenterTable().setItems(getSeachedEmployees(employees));
      searchEmployeePopup.closePopup();
    } else {
      searchEmployeePopup.setErrorLabelText(userInputErrorText.getSearchError());
    }
  }

  /**
   * Takes all inputs from popup and tries to create an employee
   * if all inputs are valid.
   */
  private void buildEmployee() {
    boolean inputValid;

    String firstName = addEmployeePopup.getFirstName().trim();
    inputValid = userInputChecker.isFirstNameValid(firstName);
    if (!inputValid) {
      addEmployeePopup.setErrorLabelText(userInputErrorText.getFirstNameError());
      return;
    }

    String lastName = addEmployeePopup.getLastName().trim();
    inputValid = userInputChecker.isLastNameValid(lastName);
    if (!inputValid) {
      addEmployeePopup.setErrorLabelText(userInputErrorText.getLastNameError());
      return;
    }

    String salary = addEmployeePopup.getSalary();
    inputValid = userInputChecker.isSalaryValid(salary);
    if (!inputValid) {
      addEmployeePopup.setErrorLabelText(userInputErrorText.getSalaryError());
      return;
    }

    String departmentId = addEmployeePopup.getDepartment();
    inputValid = userInputChecker.isDepartmentIdValid(departmentId);
    if (!inputValid) {
      addEmployeePopup.setErrorLabelText(userInputErrorText.getDepartmentError());
      return;
    }

    Employee employee = new EmployeeBuilder()
        .setFirstName(firstName)
        .setLastName(lastName)
        .setSalary(Integer.parseInt(salary))
        .setDepartmentId(Integer.parseInt(departmentId))
        .build();

    employeeQueue.add(employee);
    writeEmployeeQueueToDatabase(employeeQueue);
    updateEmployeeTable();
    addEmployeePopup.closePopup();
  }

  private void updateEmployeeTable() {
    applicationGUI.getCenterTable().setItems(getEmployees());
  }

  /**
   * Takes a list of employees and inserts them to the database.
   */
  private void writeEmployeeQueueToDatabase(Queue<Employee> employeeQueue) {
    while (!employeeQueue.isEmpty()) {
      employeeIO.create(employeeQueue.poll());
    }

  }

  /**
   * Edits the selected employee.
   */
  private void updateEmployee() {
    boolean inputValid;

    String firstName = editEmployeePopup.getFirstName().trim();
    inputValid = userInputChecker.isFirstNameValid(firstName);
    if (!inputValid) {
      editEmployeePopup.setErrorLabelText(userInputErrorText.getFirstNameError());
      return;
    }

    String lastName = editEmployeePopup.getLastName().trim();
    inputValid = userInputChecker.isLastNameValid(lastName);
    if (!inputValid) {
      editEmployeePopup.setErrorLabelText(userInputErrorText.getLastNameError());
      return;
    }

    String salary = editEmployeePopup.getSalary();
    inputValid = userInputChecker.isSalaryValid(salary);
    if (!inputValid) {
      editEmployeePopup.setErrorLabelText(userInputErrorText.getSalaryError());
      return;
    }

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

  public void setGui(ApplicationGUI applicationGUI) {
    this.applicationGUI = applicationGUI;
  }

  /**
   * Collects all employees from database and converts them to an
   * observable list.
   *
   * @return Observable list of employees
   */
  public ObservableList<EmployeeObservable> getEmployees() {
    data.clear();
    data = toObservableList.convertList(employeeIO.read());
    return data;
  }

  /**
   * Collects searched employees from database and converts them to an
   * observable list.
   *
   * @return Observable list of employees
   */
  public ObservableList<EmployeeObservable> getSeachedEmployees(List<Employee> employees) {
    data.clear();
    data = toObservableList.convertList(employees);
    return data;
  }

  public Observers getObservers() {
    return observers;
  }


  /**
   * class containing all observers needed in the project.
   *
   * @author Harald & Cristoffer
   */
  public class Observers {

    private EventHandler<MouseEvent> mouseEvent = new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        Object object = event.getSource();

        @SuppressWarnings("unchecked")
        TableView<EmployeeObservable> tableView = (TableView<EmployeeObservable>) object;
        selectedEmployee = tableView.getSelectionModel().getSelectedItem();
      }
    };

    private EventHandler<WindowEvent> windowEvent = new EventHandler<WindowEvent>() {

      @Override
      public void handle(WindowEvent event) {
        System.out.println("Exiting App");
        hibernateSessionManager.close();
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

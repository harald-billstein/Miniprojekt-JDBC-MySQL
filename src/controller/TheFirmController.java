package controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.persistence.metamodel.ListAttribute;
import javax.swing.text.StyledEditorKit.BoldAction;
import org.hibernate.procedure.internal.Util.ResultClassesResolutionContext;
import antlr.collections.Stack;
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
  private final int MINIMUM_SALARY = 19000;

  public Observers getObservers() {
    return observers;
  }

  public TheFirmController() {}

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
      // TODO DB is down! handle?
    }
    employeeIO = new EmployeeIO(hibernateSessionManager);
    departmentIO = new DepartmentIO(hibernateSessionManager);

    data = FXCollections.observableArrayList();
    toObservableList = new ToObservableList();
    observers = new Observers();
    employeeQueue = new LinkedList<>();
  }

  private void removeEmployee() {

    if (selectedEmployee != null) {
      Boolean success = employeeIO.delete(selectedEmployee.getEmployeeId());

      if (success) {
        applicationGUI.getCenterTable().setItems(getEmployees());
      }
    }
    // TODO If no user was selected, do nothing or print out error in GUI?
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

  private void createSearchEmployeePopup() {
    searchEmployeePopup = new SearchEmployeePopup(applicationGUI.getPrimaryStage(), observers);
    searchEmployeePopup.createSearchEmployeePopupWindow();
  }

  private void createAddEmployeePopup() {
    addEmployeePopup = new AddEmployeePopup(applicationGUI.getPrimaryStage(), observers);
    addEmployeePopup.createAddEmployeePopup();
  }

  // TODO magic numbers 0,1,2
  private void createEditEmployeePopup() {
    editEmployeePopup = new EditEmployeePopup(applicationGUI.getPrimaryStage(), observers);
    if (selectedEmployee != null) {
      editEmployeePopup.createEditEmployeePopup();
      editEmployeePopup.setFirstName(selectedEmployee.getFirstName());
      editEmployeePopup.setLastName(selectedEmployee.getLastName());
      editEmployeePopup.setSalary(selectedEmployee.getSalary());

    }
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

  private void buildEmployee() {
    boolean inputValid = true;

    String firstName = addEmployeePopup.getFirstName().trim();
    if (firstName.isEmpty()) {
      addEmployeePopup.setErrorLabelText("First name can not be empty");
      inputValid = false;
    }

    String lastName = addEmployeePopup.getLastName().trim();
    if (lastName.isEmpty() && inputValid) {
      addEmployeePopup.setErrorLabelText("Last name can not be empty");
      inputValid = false;
    }

    String salary = addEmployeePopup.getSalary();
    if (inputValid) {
      if (!isParsable(salary)) {
        addEmployeePopup.setErrorLabelText("Salary must be a number");
        inputValid = false;
      } else if (Integer.parseInt(salary) < MINIMUM_SALARY) {
        addEmployeePopup.setErrorLabelText("Minimum salary is: " + MINIMUM_SALARY);
        inputValid = false;
      }
    }

    String departmentId = addEmployeePopup.getDepartment();
    if (inputValid) {
      int departmentSize = departmentIO.read().size();
      if (!isParsable(departmentId)) {
        addEmployeePopup.setErrorLabelText("Department Id must be a number");
        inputValid = false;
      } else if (Integer.parseInt(departmentId) < 1
          || Integer.parseInt(departmentId) > departmentSize) {
        addEmployeePopup.setErrorLabelText("Department not found");
        inputValid = false;
      }
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
    if (firstName.isEmpty()) {
      editEmployeePopup.setErrorLabelText("First name can not be empty");
      inputValid = false;
    }

    String lastName = editEmployeePopup.getLastName().trim();
    if (lastName.isEmpty() && inputValid) {
      editEmployeePopup.setErrorLabelText("Last name can not be empty");
      inputValid = false;
    }

    String salary = editEmployeePopup.getSalary();
    if (inputValid) {
      if (!isParsable(salary)) {
        editEmployeePopup.setErrorLabelText("Salary must be a number");
        inputValid = false;
      } else if (Integer.parseInt(salary) < MINIMUM_SALARY) {
        editEmployeePopup.setErrorLabelText("Minimum salary is: " + MINIMUM_SALARY);
        inputValid = false;
      }
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

  private boolean isParsable(String s) {
    boolean success;

    try {
      Integer.parseInt(s);
      success = true;
    } catch (NumberFormatException e) {
      success = false;
    }
    return success;
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

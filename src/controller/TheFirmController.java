package controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.omg.CORBA.PRIVATE_MEMBER;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import model.CompanyCar;
import model.Department;
import model.Employee;
import model.EmployeeBuilder;
import model.EmployeeObservable;
import model.Pair;
import model.ToObservableList;
import view.AddEmployeePopup;
import view.ApplicationGUI;
import view.SearchEmployeePopup;
import view.WebPagePresenter;

public class TheFirmController implements EventHandler<ActionEvent> {

  private HibernateSessionManager hibernateSessionManager;
  private ApplicationGUI applicationGUI;
  private EmployeeIO employeeIO;
  private CompanyCarIO companyCarIO;
  private DepartmentIO departmentIO;
  private ToObservableList toObservableList;
  private ObservableList<EmployeeObservable> data = FXCollections.observableArrayList();
  private SearchEmployeePopup searchEmployeePopup;
  private AddEmployeePopup addEmployeePopup;
  private Observers observers;
  private EmployeeObservable selectedEmployee;

  public Observers getObservers() {
    return observers;
  }

  public TheFirmController() {}

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

    toObservableList = new ToObservableList();
    observers = new Observers();
    
 

  }



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
      WebPagePresenter webPagePresenter = new WebPagePresenter(applicationGUI.getPrimaryStage());
      webPagePresenter.showGitPage();
    }
  }

  private void removeEmployee() {
  
    Boolean success = employeeIO.delete(selectedEmployee.getEmployeeId());
    
    if (success) {
      System.out.println("successs!");
      applicationGUI.getCenterTable().setItems(getEmployees());
    }
    
    // TODO else? what do we want to do?
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
    searchEmployeePopup = new SearchEmployeePopup(applicationGUI.getPrimaryStage(), this);
    searchEmployeePopup.createSearchEmployeePopupWindow();
  }

  private void createAddEmployeePopup() {
    addEmployeePopup = new AddEmployeePopup(applicationGUI.getPrimaryStage(), this);
    addEmployeePopup.createAddEmployeePopup();
  }

  private void doEmployeeSearch() {
    if (searchEmployeePopup.getEmployeeNameInput().length() > 0) {
      List<Employee> employees =
          employeeIO.seachEmployeeName(searchEmployeePopup.getEmployeeNameInput());
      applicationGUI.getCenterTable().setItems(getSeachedEmployees(employees));
      searchEmployeePopup.closePopup();
    } else
      searchEmployeePopup.toggleErrorLabelText();
  }

  private void addNewEmployee() {
    LinkedList<Pair> employeeData = addEmployeePopup.getEmployeeData();

    Employee employee = new EmployeeBuilder().setFirstName(employeeData.pop().getValue())
        .setLastName(employeeData.pop().getValue())
        .setSalary(Integer.parseInt(employeeData.pop().getValue()))
        .setDepartmentId(Integer.parseInt(employeeData.pop().getValue())).build();

    employeeIO.create(employee);
    addEmployeePopup.closePopup();
  }
  
  public class Observers {
    
    // TODO move ActionEvent to this innerclass to have all our Observers in the same place
    
    private EventHandler<MouseEvent> mouseEvent = new EventHandler<MouseEvent>() {
      
      @Override
      public void handle(MouseEvent event) {
        System.out.print("HEJ ");
        Object object = event.getSource();
        TableView<EmployeeObservable> tableView = ((TableView<EmployeeObservable>) object);
        selectedEmployee = tableView.getSelectionModel().getSelectedItem();
        System.out.println(selectedEmployee.getEmployeeId() + " " + selectedEmployee.getFirstName());
        
      }
    };

    public EventHandler<MouseEvent> getMouseEvent() {
      return mouseEvent;
    }
  }
}

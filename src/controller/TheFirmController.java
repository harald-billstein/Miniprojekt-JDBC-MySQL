package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import model.CompanyCar;
import model.Department;
import model.Employee;
import model.EmployeeObservable;
import model.ToObservableList;
import view.ApplicationGUI;

public class TheFirmController implements EventHandler<ActionEvent> {

	private HibernateSessionManager hibernateSessionManager;
	private ApplicationGUI applicationGUI;
	private EmployeeIO employeeIO;
	private CompanyCarIO companyCarIO;
	private DepartmentIO departmentIO;
	private ToObservableList toObservableList;
	private ObservableList<EmployeeObservable> data = FXCollections.observableArrayList();

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
		
		toObservableList = new ToObservableList();
		
	}
	
	

	@Override
	public void handle(ActionEvent event) {
			Button button = (Button) event.getSource();
			
			if (button.getText().equals("Search")) {
				System.out.println(button.getText());
				List<Employee> employees = employeeIO.seachEmployeeName("we");
				applicationGUI.getCenterTable().setItems(getSeachedEmployees(employees));
				
				
			} else if (button.getText().equals("Add")) {
				System.out.println(button.getText());
			} else if (button.getText().equals("Remove")) {
				System.out.println(button.getText());
			} else if (button.getText().equals("Edit")) {
				System.out.println(button.getText());
			}	
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
}
 
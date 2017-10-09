package controller;

import java.util.ArrayList;
import java.util.List;

import databasecontroller.EmployeeIO;
import databasecontroller.HibernateSessionManager;
import databasemodel.CompanyCar;
import databasemodel.Department;
import databasemodel.Employee;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.ApplicationGUI;

public class TheFirmController implements EventHandler<ActionEvent> {

	public HibernateSessionManager hibernateSessionManager;
	private EmployeeIO employeeIO;

	public TheFirmController() {
	}

	public void start() {
		System.out.println("Controller start");
		kickstartControllerresources();
		ApplicationGUI applicationGUI = new ApplicationGUI();
		applicationGUI. kickstartGUIRecources(this);
	}

	private void kickstartControllerresources() {
		System.out.println("Controller Kickstaretresources");

		List<Class<?>> clazzes = new ArrayList<Class<?>>();
		clazzes.add(Employee.class);
		clazzes.add(Department.class);
		clazzes.add(CompanyCar.class);

		try {
			hibernateSessionManager = new HibernateSessionManager(clazzes);
		} catch (Exception e) {

		}
		employeeIO = new EmployeeIO(hibernateSessionManager);
	}

	@Override
	public void handle(ActionEvent event) {
		System.out.println("callback working");
	}
}

package main;

import java.util.ArrayList;
import java.util.List;

import controller.CompanyCarIO;
import controller.DepartmentIO;
import controller.EmployeeIO;
import controller.HibernateSessionManager;
import controller.TheFirmController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.CompanyCar;
import model.Department;
import model.Employee;
import view.ApplicationGUI;

public class Main extends Application {

	public static void main(String[] args) {
		// TURN OFF HIBERNATE LOGGER
		//java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		// TheFirm theFirm = new TheFirm();
		// theFirm.start();

		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		TheFirmController theFirmController = new TheFirmController();
		theFirmController.start();
		ApplicationGUI applicationGUI = new ApplicationGUI();
		theFirmController.setGui(applicationGUI);

		applicationGUI.setObservers(theFirmController.getObservers());
		applicationGUI.setPrimaryStage(primaryStage);
		applicationGUI.start();
		applicationGUI.getCenterTable().setItems(theFirmController.getEmployees());







//		HibernateSessionManager hibernateSessionManager = null;
//
//		List<Class<?>> clazzes = new ArrayList<Class<?>>();
//		clazzes.add(Employee.class);
//		clazzes.add(Department.class);
//		clazzes.add(CompanyCar.class);
//
//		try {
//			hibernateSessionManager = new HibernateSessionManager(clazzes);
//		} catch (Exception e) {
//
//		}
//		EmployeeIO employeeIO = new EmployeeIO(hibernateSessionManager);
//		CompanyCarIO companyCarIO = new CompanyCarIO(hibernateSessionManager);
//		DepartmentIO departmentIO = new DepartmentIO(hibernateSessionManager);
//
//		List<Employee> employees = employeeIO.read();
//		List<CompanyCar> companyCar = companyCarIO.read();
//		List<Department> department = departmentIO.read();
//
//		for (Employee employee: employees) {
//			System.out.print(employee.getFname() + " ");
//			System.out.print(employee.getDepartment().getName());
//			//System.out.println(employee.getCompanycar().brand());
//			System.out.println();
//		}
//
//
//		for (CompanyCar companyCars: companyCar) {
//			System.out.print(companyCars.getBrand() + " ");
//			System.out.print(companyCars.getModel());
//			System.out.println();
//		}








	}
}
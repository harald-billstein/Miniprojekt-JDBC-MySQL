package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.CompanyCarIO;
import controller.DatabaseInfoIO;
import controller.DepartmentIO;
import controller.EmployeeIO;
import controller.HibernateSessionManager;
import model.*;
import view.ConsoleView;

class TheFirm extends ConsoleView {

	private Scanner scanner;
	boolean runApplication;
	private HibernateSessionManager hibernateSessionManager;
	private EmployeeIO employeeIO;
	private CompanyCarIO companyCarIO;
	private DepartmentIO departmentIO;
	private DatabaseInfoIO databaseInfoIO;

	void start() {
		kickstartresources();
		showMenu();
	}

	private void kickstartresources() {
		scanner = new Scanner(System.in);
		
		List<Class<?>> clazzes = new ArrayList<Class<?>>();
		clazzes.add(Employee.class);
		clazzes.add(Department.class);
		clazzes.add(CompanyCar.class);
		
		try {
			hibernateSessionManager = new HibernateSessionManager(clazzes);
		} catch (Exception e) {
			System.out.print("Failed, database online?, Try again? yes/no: ");

			if (scanner.nextLine().equalsIgnoreCase("yes")) {
				start();
			} else {
				close();
			}
		}
		employeeIO = new EmployeeIO(hibernateSessionManager);
		companyCarIO = new CompanyCarIO(hibernateSessionManager);
		departmentIO = new DepartmentIO(hibernateSessionManager);
		databaseInfoIO = new DatabaseInfoIO(hibernateSessionManager);
	}

	private void showMenu() {
		runApplication = true;

		while (runApplication) {
			System.out.println(databaseInfoIO.getUptime());
			printMenMenu();
			String answer = scanner.nextLine();

			boolean isParable = isParsable(answer);

			if (isParable) {
				MenuChoice(Integer.parseInt(answer));
			}
		}
	}

	private boolean isParsable(String s) {
		boolean success = false;
		try {
			Integer.parseInt(s);
			success = true;
		} catch (Exception e) {
			System.out.println("Error parsing string. " + e.getMessage());
		}
		return success;
	}

	private void MenuChoice(int answer) {
		switch (answer) {
		case 1:
			showEmployees();
			break;
		case 2:
			showDepartments();
			break;
		case 3:
			showAllCompanyCars();
			break;
		case 4:
			addNewEmployee();
			break;
		case 5:
			updateSalary();
			break;
		case 6:
			removeEmployee();
			break;
		case 7:
			searchEmployee();
			break;
		case 8:
			listAllEmployeesFromDepartment();
			break;
		case 9:
			close();
			break;
		default:
			System.out.println("Not a valied menu option \n");
			showMenu();
			break;
		}
	}

	private void listAllEmployeesFromDepartment() {
		String departmentId;
		printDepartments(departmentIO.read());

		do {
			System.out.print("Enter department id: ");
			departmentId = scanner.nextLine();
		} while (!isParsable(departmentId));

		printEmployeesIncludingDepartment(employeeIO.getDepartmentEmployeeList(Integer.parseInt(departmentId)));
		System.out.println("Press any key...");
		scanner.nextLine();
	}

	private void searchEmployee() {
		System.out.print("Name: ");
		String name = scanner.nextLine();
		printEmployees(employeeIO.seachEmployeeName(name));

		System.out.println("Press any key...");
		scanner.nextLine();
	}

	private void removeEmployee() {
		String employeeId;
		do {
			System.out.println("Enter ID of employee to remove: ");
			employeeId = scanner.nextLine();
		} while (!isParsable(employeeId));

		try {
			employeeIO.delete(Integer.parseInt(employeeId));
			System.out.println("Success!");
		} catch (Exception e) {
			System.out.println("Error removing employee: " + e.getMessage());
		}

		System.out.println("Press any key...");
		scanner.nextLine();
	}

	private void updateSalary() {
		String employeeId;
		do {
			System.out.print("Employee id: ");
			employeeId = scanner.nextLine();
		} while (!isParsable(employeeId));

		String salary;
		do {
			System.out.print("Salary: ");
			salary = scanner.nextLine();
		} while (!isParsable(salary));

		Employee employee = new Employee();
		try {
			employee.setEmployeeId(Integer.parseInt(employeeId));
			employee.setSalary(Integer.parseInt(salary));
			employeeIO.updateEmployee(employee);
			System.out.println("Success!");
		} catch (Exception e) {
			System.out.println("Could not find employee, " + e.getMessage());
		}

		System.out.println("Press any key...");
		scanner.nextLine();
	}

	private void addNewEmployee() {
		System.out.print("First name: ");
		String firstName = scanner.nextLine();

		System.out.print("Last name: ");
		String lastName = scanner.nextLine();

		String salary;
		do {
			System.out.print("Salary: ");
			salary = scanner.nextLine();
		} while (!isParsable(salary));

		String departmentId;
		do {
			System.out.print("Department id: ");
			departmentId = scanner.nextLine();
		} while (!isParsable(departmentId));

		Employee employee = new EmployeeBuilder()
		        .setFirstName(firstName)
		        .setLastName(lastName)
		        .setSalary(Integer.parseInt(salary))
		        .setDepartmentId(Integer.parseInt(departmentId))
		        .build();
		    
		try {
			employeeIO.create(employee);

		} catch (Exception e) {
			System.out.println("Error saving employee, " + e.getMessage());
		}

		System.out.println("Press any key...");
		scanner.nextLine();
	}

	private void showAllCompanyCars() {
		printCompanyCars(companyCarIO.read());

		System.out.println("Press any key...");
		scanner.nextLine();
	}

	private void showDepartments() {
		printDepartments(departmentIO.read());

		System.out.println("Press any key...");
		scanner.nextLine();
	}

	private void showEmployees() {
		printEmployees(employeeIO.read());

		System.out.println("Press any key...");
		scanner.nextLine();
	}

	private void close() {
		runApplication = false;
		try {
			hibernateSessionManager.close();
			System.in.close();
			scanner.close();
			System.out.println("EXITING.....");
			System.out.println("DONE");
			System.exit(0);

		} catch (IOException e) {
			System.exit(-1);
			e.printStackTrace();
			System.out.println("Something went wrong when closing the program: " + e.getMessage());
		}
	}
}

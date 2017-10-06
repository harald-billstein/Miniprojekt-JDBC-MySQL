package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import databasemodel.*;
import view.ConsoleView;
import databasecontroller.CompanyCarIO;
import databasecontroller.DepartmentIO;
import databasecontroller.EmployeeIO;
import databasecontroller.HibernateSessionManager;

class TheFirm extends ConsoleView {

	private Scanner scanner;
	boolean runApplication;
	
	private HibernateSessionManager hibernateSessionManager;
	private EmployeeIO employeeIO;
	private CompanyCarIO companyCarIO;
	private DepartmentIO departmentIO;

	void start() {
		scanner = new Scanner(System.in);
		try {
			List<Class<?>> clazzes = new ArrayList<Class<?>>();
			clazzes.add(Employee.class);
			clazzes.add(Department.class);
			clazzes.add(CompanyCar.class);
			hibernateSessionManager = new HibernateSessionManager(clazzes);
			employeeIO = new EmployeeIO(hibernateSessionManager);
			companyCarIO = new CompanyCarIO(hibernateSessionManager);
			departmentIO = new DepartmentIO(hibernateSessionManager);
			
		} catch (Exception e) {
			System.out.println("Exception thrown " + e.getMessage());
			System.out.print("Try again? yes/no: ");

			if (scanner.nextLine().equalsIgnoreCase("yes")) {
				start();
			} else {
				System.out.println("shut of system");
				close();
			}
		}
		System.out.println(employeeIO.getDatabaseInfo(hibernateSessionManager.getSession()));
		showMenu();
	}

	private void showMenu() {

		runApplication = true;

		while (runApplication) {

			System.out.println("#################################################");
			System.out.println("1: Show all employees                           |");
			System.out.println("2: Show departments                             |");
			System.out.println("3: Show all company cars                        |");
			System.out.println("4: Add new Employee                             |");
			System.out.println("5: Update salary (Not working)                  |");
			System.out.println("6: Remove employee                              |");
			System.out.println("7: Search for an employee                       |");
			System.out.println("8: List all employees from specific department  |");
			System.out.println("9: Exit program                                 |");
			System.out.println("#################################################");
			System.out.print("--> ");
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
		// TODO: do while? Retry until employee is found.
		try {
			Employee employee = new Employee();
			employee.setEmployee_id(Integer.parseInt(employeeId));
			employee.setSalary(Integer.parseInt(salary));
			employeeIO.updateEmployee(employee);
			System.out.println("Success!");
		} catch (Exception e) {
			System.out.println("Could not find employee." + e.getMessage());
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

		Employee employee = new EmployeeBuilder().setFname(firstName).setLname(lastName)
				.setSalary(Integer.parseInt(salary)).setDepartmentId(Integer.parseInt(departmentId)).build();

		try {
			EmployeeIO employeeIO = new EmployeeIO(hibernateSessionManager);
			employeeIO.create(employee);

		} catch (Exception e) {
			System.out.println("Error saving employee. " + e.getMessage());
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
			// close hibernate factory etc
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

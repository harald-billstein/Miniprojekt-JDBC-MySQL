import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.hibernate.criterion.BetweenExpression;
import databasemodel.*;

import databasecontroller.TheFirmDatabaseIO;
import databasemodel.CompanyCar;
import databasemodel.Department;
import databasemodel.EmployeeBuilder;
import exceptions.PrintException;
import exceptions.TheFirmParsebleException;

public class TheFirm<T> {

	private Scanner scanner = new Scanner(System.in);
	private TheFirmDatabaseIO<?> theFirmDatabaseIO = new TheFirmDatabaseIO<>();
	private Class<?> Employee;

	public void start() {
		System.out.println(theFirmDatabaseIO.getDatabaseInfo());
		showMenu();
	}

	public void showMenu() {
		System.out.println("##########################");
		System.out.println("1: Show all employees");
		System.out.println("2: Show departments");
		System.out.println("3: Show all company cars");
		System.out.println("4: Add new Employee");
		System.out.println("5: Update salary");
		System.out.println("6: Remove employee");
		System.out.println("7: Search for an employee");
		System.out.println("8: List all employees from specific department");
		System.out.println("9: Exit program");
		System.out.println("##########################");
		System.out.print("--> ");
		String answer = scanner.nextLine();

		boolean isParable = isParsable(answer);

		if (isParable) {
			MenuChoice(Integer.parseInt(answer));
		} else {
			showMenu();
		}
	}

	private boolean isParsable(String s) {
		boolean success = false;
		try {
			Integer.parseInt(s);
			success = true;
		} catch (Exception e) {
			new TheFirmParsebleException(e);
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
			System.out.println("EXITING.....");
			System.out.println("DONE");
			System.exit(0);
			break;

		default:
			System.out.println("Not a valied menu option \n");
			showMenu();
			break;
		}
	}

	private void listAllEmployeesFromDepartment() {
		System.out.println("listAllEmployeesFromDepartment");
		// TODO Auto-generated method stub
		System.out.println("Press any key...");
		scanner.nextLine();
		showMenu();

	}

	private void searchEmployee() {
		System.out.print("Name: ");
		String name = scanner.nextLine();
		theFirmDatabaseIO.setClazz(Employee.class);
		List<Employee> employees = theFirmDatabaseIO.seachEmployeeName(name);
		printEmployees(employees);
		System.out.println("Press any key...");
		scanner.nextLine();
		showMenu();
	}

	private void removeEmployee() {
		System.out.println("removeEmployee");
		// TODO Auto-generated method stub
		System.out.println("Press any key...");
		scanner.nextLine();
		showMenu();
	}

	private void updateSalary() {
		theFirmDatabaseIO.setClazz(Employee.class);

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
		
		try {
			theFirmDatabaseIO.update(Integer.parseInt(employeeId), Integer.parseInt(salary));
		} catch (Exception e) {
			System.out.println("Error");
		}
		
		System.out.println("Press any key...");
		scanner.nextLine();
		showMenu();
	}

	private void addNewEmployee() {
		System.out.print("First name: ");
		String firstName = scanner.nextLine();
		System.out.print("Last name: ");
		String lastName = scanner.nextLine();
		
		
		// TODO hämmta department och kolla antal, så du inte lägger till person på
		// avdelning som inte finns (crash)!
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
				.setFname(firstName)
				.setLname(lastName)
				.setSalary(Integer.parseInt(salary))
				.setDepartmentId(Integer.parseInt(departmentId))
				.build();

		theFirmDatabaseIO.setClazz(Employee.class);
		theFirmDatabaseIO.save(employee);

		System.out.println("Press any key...");
		scanner.nextLine();
		showMenu();
	}

	private void showAllCompanyCars() {
		theFirmDatabaseIO.setClazz(CompanyCar.class);
		List<CompanyCar> companyCars = (List<CompanyCar>) theFirmDatabaseIO.retrive("company_car");
		printCompanyCars(companyCars);
		System.out.println("Press any key...");
		scanner.nextLine();
		showMenu();
	}

	private void showDepartments() {
		theFirmDatabaseIO.setClazz(Department.class);
		printDepartments((List<Department>) theFirmDatabaseIO.retrive("department"));
		System.out.println("\nPress any key...");
		scanner.nextLine();
		showMenu();
	}

	private void showEmployees() {
		theFirmDatabaseIO.setClazz(Employee.class);
		List<Employee> employees = (List<Employee>) theFirmDatabaseIO.retrive("employee");
		printEmployees(employees);
		System.out.println("Press any key...");
		scanner.nextLine();
		showMenu();
	}

	public void printEmployees(List<Employee> employees) {
		for (Employee employee : employees) {
			System.out.println(employee.getFname() + " " + employee.getLname());
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
				new PrintException(e);
			}
		}
		System.out.println();
	}

	private void printCompanyCars(List<CompanyCar> companyCars) {
		for (CompanyCar companyCar : companyCars) {
			System.out.println(companyCar.getReg_nr() + " - " + companyCar.getBrand() + " - " + companyCar.getModel());
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
				new PrintException(e);
			}
		}
	}

	private void printDepartments(List<Department> departments) {
		for (Department department : departments) {
			System.out.println(department.getName());
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
				new PrintException(e);
			}
		}
	}

	public void clearScreen() {
		// TODO can this be done?
	}
}

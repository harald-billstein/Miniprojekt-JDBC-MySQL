import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import databasemodel.*;

import databasecontroller.TheFirmDatabaseIO;
import exceptions.AddEmployeeException;
import exceptions.CloseProgramException;
import exceptions.PrintException;
import exceptions.RemoveEmployeeException;
import exceptions.TheFirmParsebleException;
import exceptions.UpdateSalaryException;

public class TheFirm {

	private Scanner scanner;
	private TheFirmDatabaseIO<?> theFirmDatabaseIO;

	public void start() {
		scanner = new Scanner(System.in);
		theFirmDatabaseIO = new TheFirmDatabaseIO<>();
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
		do {
			System.out.print("Enter department id: ");
			departmentId = scanner.nextLine();
		} while (!isParsable(departmentId));
		
		List<Employee> employeesFromDepartment = (List<Employee>) theFirmDatabaseIO.retriveDepartmentEmployeeList(Integer.parseInt(departmentId));
		printEmployeesIncludingDepartment(employeesFromDepartment);
		System.out.println("Press any key...");
		scanner.nextLine();
		showMenu();
	}

	private void searchEmployee() {
		System.out.print("Name: ");
		String name = scanner.nextLine();
		
		List<Employee> employees = (List<Employee>) theFirmDatabaseIO.seachEmployeeName(name);
		printEmployees(employees);
		
		System.out.println("Press any key...");
		scanner.nextLine();
		showMenu();
	}

	private void removeEmployee() {
		String employeeId;
		do {
			System.out.println("Enter ID of employee to remove: ");
			employeeId = scanner.nextLine();
		} while (!isParsable(employeeId));

		try {
			theFirmDatabaseIO.delete(Employee.class, Integer.parseInt(employeeId));
			System.out.println("Success!");
		} catch (Exception e) {
			new RemoveEmployeeException(e);
		}

		System.out.println("Press any key...");
		scanner.nextLine();
		showMenu();
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
		
		try {
			theFirmDatabaseIO.update(Integer.parseInt(employeeId), Integer.parseInt(salary));
			System.out.println("Succsses!");
		} catch (Exception e) {
			new UpdateSalaryException(e);
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
		
		try {
			theFirmDatabaseIO.save(employee);			
		} catch (Exception e) {
			new AddEmployeeException(e);
		}

		System.out.println("Press any key...");
		scanner.nextLine();
		showMenu();
	}

	private void showAllCompanyCars() {
		List<CompanyCar> companyCars = (List<CompanyCar>) theFirmDatabaseIO.retrive("company_car");
		printCompanyCars(companyCars);
		
		System.out.println("Press any key...");
		scanner.nextLine();
		showMenu();
	}

	private void showDepartments() {
		printDepartments((List<Department>) theFirmDatabaseIO.retrive("department"));
		
		System.out.println("Press any key...");
		scanner.nextLine();
		showMenu();
	}

	private void showEmployees() {
		List<Employee> employees = (List<Employee>) theFirmDatabaseIO.retrive("employee");
		printEmployees(employees);
		
		System.out.println("Press any key...");
		scanner.nextLine();
		showMenu();
	}

	public void printEmployees(List<Employee> employees) {
		if (employees.isEmpty()) {
			System.out.println("Empty result!");
		}
		
		for (Employee employee : employees) {
			System.out.println(employee.getEmployee_id() + " " + employee.getFname() + " " + employee.getLname());
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
				new PrintException(e);
			}
		}
		System.out.println();
	}
	
	public void printEmployeesIncludingDepartment(List<Employee> employees) {
		if (employees.isEmpty()) {
			System.out.println("Empty result!");
		}
		
		for (Employee employee : employees) {
			System.out.println(employee.getFname() + " " + employee.getLname() + " " + employee.getDepartment().getName() );
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
		if (companyCars.isEmpty()) {
			System.out.println("Empty result!");
		}
		
		for (CompanyCar companyCar : companyCars) {
			System.out.println("Reg: " + companyCar.getReg_nr() + " Brand: " + companyCar.getBrand() + " Model: " + companyCar.getModel());
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
				new PrintException(e);
			}
		}
	}

	private void printDepartments(List<Department> departments) {
		if (departments.isEmpty()) {
			System.out.println("Empty result!");
		}
		
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
	
	public void close() {
		try {
			theFirmDatabaseIO.close();
			System.in.close();
			scanner.close();
			System.out.println("EXITING.....");
			System.out.println("DONE");
			System.exit(0);
			
		} catch (IOException e) {
			System.out.println("EXITING.....");
			System.out.println("DONE");
			System.exit(-1);
			e.printStackTrace();
			new CloseProgramException(e);
		}
	}
}

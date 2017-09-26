import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import databasecontroller.TheFirmDatabaseIO;
import databasemodel.Employee;
import exceptions.TheFirmParsebleException;

public class TheFirm<T> {

	private Scanner scanner = new Scanner(System.in);
	private TheFirmDatabaseIO<?> theFirmDatabaseIO = new TheFirmDatabaseIO<>();

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
		System.out.println("7: Search for an eployee");
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
		theFirmDatabaseIO = new TheFirmDatabaseIO<>(Employee.class);
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
		System.out.println("updateSalary");
		// TODO Auto-generated method stub
		System.out.println("Press any key...");
		scanner.nextLine();
		showMenu();
	}

	private void addNewEmployee() {
		System.out.println("addNewEmployee");
		// TODO Auto-generated method stub
		System.out.println("Press any key...");
		scanner.nextLine();
		showMenu();
	}

	private void showAllCompanyCars() {
		System.out.println("showAllCompanyCars");
		// TODO Auto-generated method stub
		System.out.println("Press any key...");
		scanner.nextLine();
		showMenu();
	}

	private void showDepartments() {
		System.out.println("showDepartments");
		// TODO Auto-generated method stub
		System.out.println("Press any key...");
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println();
	}

	public void clearScreen() {
		// TODO can this be done?
	}
}

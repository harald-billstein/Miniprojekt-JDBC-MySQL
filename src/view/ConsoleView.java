package view;

import java.util.List;
import java.util.concurrent.TimeUnit;

import model.CompanyCar;
import model.Department;
import model.Employee;

public class ConsoleView {

	public void printMenMenu() {
		System.out.println("#################################################");
		System.out.println("1: Show all employees                           |");
		System.out.println("2: Show departments                             |");
		System.out.println("3: Show all company cars                        |");
		System.out.println("4: Add new Employee                             |");
		System.out.println("5: Update salary                                |");
		System.out.println("6: Remove employee                              |");
		System.out.println("7: Search for an employee                       |");
		System.out.println("8: List all employees from specific department  |");
		System.out.println("9: Exit program                                 |");
		System.out.println("#################################################");
		System.out.print("--> ");
	}

	public void printEmployees(List<Employee> employees) {

		if (employees.isEmpty()) {
			System.out.println("Empty result!");
		} else {
			for (Employee employee : employees) {
				System.out.println(employee.getEmployee_id() + " " + employee.getFname() + " " + employee.getLname()
						+ " Salary: " + employee.getSalary());
				try {
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("TimeUnit sleep exception: " + e.getMessage());
				}
			}
		}

		System.out.println();
	}

	public void printEmployeesIncludingDepartment(List<Employee> employees) {

		if (employees.isEmpty()) {
			System.out.println("Empty result!");
		} else {
			for (Employee employee : employees) {
				System.out.println(
						employee.getFname() + " " + employee.getLname() + " " + employee.getDepartment().getName());
				try {
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("TimeUnit sleep exception: " + e.getMessage());
				}
			}
		}
		System.out.println();
	}

	public void printDepartments(List<Department> departments) {

		if (departments.isEmpty()) {
			System.out.println("Empty result!");
		} else {
			for (Department department : departments) {
				System.out.println(department.getDepartment_id() + ". " + department.getName());
				try {
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("TimeUnit sleep exception: " + e.getMessage());
				}
			}
		}
	}

	public void printCompanyCars(List<CompanyCar> companyCars) {

		if (companyCars.isEmpty()) {
			System.out.println("Empty result!");
		} else {
			for (CompanyCar companyCar : companyCars) {
				System.out.printf("%-20s %-14s %-15s %-15s %n", "Employee_id: " + companyCar.getEmployee_id(),
						" Reg: " + companyCar.getReg_nr().toUpperCase(), "Brand: " + companyCar.getBrand(),
						" Model: " + companyCar.getModel());
				try {
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("TimeUnit sleep exception: " + e.getMessage());
				}
			}
		}
	}
}

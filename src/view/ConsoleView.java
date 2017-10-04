package view;

import java.util.List;
import java.util.concurrent.TimeUnit;

import databasemodel.CompanyCar;
import databasemodel.Department;
import databasemodel.Employee;

public class ConsoleView {
	
	
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
	
	public static void printCompanyCars(List<CompanyCar> companyCars) {
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

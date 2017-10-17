package model;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ToObservableList {
	
	public ToObservableList() {
		
	}
	
	public ObservableList<EmployeeObservable> convertList(List<Employee> employess) {
		ObservableList<EmployeeObservable> employeeObservableList = FXCollections.observableArrayList();
		
		for (Employee employee : employess) {
			EmployeeObservable employeeObservable = new EmployeeObservable();
			employeeObservable.setFirstName(employee.getFname());
			employeeObservable.setLastName(employee.getLname());
			employeeObservable.setSalary(employee.getSalary());
			employeeObservable.setHireDate(employee.getHire_date());
			employeeObservable.setDepartmentName(employee.getDepartment().getName());
			employeeObservable.setDepartmentRent(employee.getDepartment().getRent());
			employeeObservable.setDepartmentPhoneNumber(employee.getDepartment().getPhone_number());
			employeeObservable.setCompanyCarRegNr(employee.getCompanyCar().getReg_nr());
			employeeObservable.setCompanyCarBrand(employee.getCompanyCar().getBrand());
			employeeObservable.setCompanyCarModel(employee.getCompanyCar().getModel());
			employeeObservable.setCompanyCarPurchasePrise(employee.getCompanyCar().getPurchase_price());
			employeeObservable.setCompanyCarPurchaseDate(employee.getCompanyCar().getPurchase_date());
			employeeObservableList.add(employeeObservable);
		}	
		return employeeObservableList;
	}
}

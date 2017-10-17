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
      EmployeeObservable employeeObs = new EmployeeObservable();
      employeeObs.setFirstName(employee.getFirstName());
      employeeObs.setLastName(employee.getLastName());
      employeeObs.setSalary(employee.getSalary());
      employeeObs.setHireDate(employee.getHireDate());
      employeeObs.setDepartmentName(employee.getDepartment().getDepartmentName());
      employeeObs.setDepartmentRent(employee.getDepartment().getDepartmentRent());
      employeeObs.setDepartmentPhoneNumber(employee.getDepartment().getDepartmentPhoneNumber());

      if (employee.getCompanyCar() != null) {
        employeeObs.setCompanyCarRegNr(employee.getCompanyCar().getCarRegNr());
        employeeObs.setCompanyCarBrand(employee.getCompanyCar().getCarBrand());
        employeeObs.setCompanyCarModel(employee.getCompanyCar().getCarModel());
        employeeObs.setCompanyCarPurchasePrise(employee.getCompanyCar().getCarPurchasePrice());
        employeeObs.setCompanyCarPurchaseDate(employee.getCompanyCar().getCarPurchaseDate());
      } else {
        employeeObs.setCompanyCarRegNr(null);
        employeeObs.setCompanyCarBrand(null);
        employeeObs.setCompanyCarModel(null);
        employeeObs.setCompanyCarPurchasePrise(null);
        employeeObs.setCompanyCarPurchaseDate(null);
      }

      employeeObservableList.add(employeeObs);
    }
    return employeeObservableList;
  }
}

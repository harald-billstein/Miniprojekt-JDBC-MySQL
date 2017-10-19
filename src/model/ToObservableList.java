package model;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class converting a List of Employee to a list of EmployeeObservable. Making the list of employees
 * compatible with the JavaFx TableView.
 * 
 * @author Harald & Cristoffer
 *
 */
public class ToObservableList {

  public ToObservableList() {

  }

  /**
   * Converts a List of Employees to a list of employeeObservable.
   * @param List of employees
   * @return Observable list of observable employees
   */
  public ObservableList<EmployeeObservable> convertList(List<Employee> employess) {
    ObservableList<EmployeeObservable> employeeObservableList = FXCollections.observableArrayList();

    for (Employee employee : employess) {
      EmployeeObservable employeeObs = new EmployeeObservable();
      employeeObs.setEmployeeId(employee.getEmployeeId());
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

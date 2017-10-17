package model;

import java.sql.Date;

public class EmployeeBuilder {
  private String firstName;
  private String lastName;
  private Integer salary;
  private Date hireDate;
  private Integer departmentId;

  public EmployeeBuilder setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public EmployeeBuilder setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public EmployeeBuilder setSalary(Integer salary) {
    this.salary = salary;
    return this;
  }

  public EmployeeBuilder setHireDate(Date hireDate) {
    this.hireDate = hireDate;
    return this;
  }

  public EmployeeBuilder setDepartmentId(Integer departmentId) {
    this.departmentId = departmentId;
    return this;
  }

  public Employee build() {
    return new Employee(this.firstName, this.lastName, this.salary, this.hireDate,
        this.departmentId);
  }
  
}

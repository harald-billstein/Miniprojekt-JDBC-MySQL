package model;

public class DepartmentBuilder {

  private String departmentName;
  private Integer departmentRent;
  private String departmentPhoneNumber;

  public DepartmentBuilder setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
    return this;
  }

  public DepartmentBuilder setDepartmentRent(Integer departmentRent) {
    this.departmentRent = departmentRent;
    return this;
  }

  public DepartmentBuilder setDepartmentPhoneNumber(String departmentPhoneNumber) {
    this.departmentPhoneNumber = departmentPhoneNumber;
    return this;
  }

  public Department Build() {
    return new Department(this.departmentName, this.departmentRent, this.departmentPhoneNumber);
  }
}

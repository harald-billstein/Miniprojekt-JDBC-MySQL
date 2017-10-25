package model;

/**
 * A builder class building department objects.
 *
 * @author Harald & Cristoffer
 */
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

  /**
   * Method takes all parameters from this class and sets them on the department object.
   *
   * @return a complete department object.
   */
  public Department Build() {
    return new Department(this.departmentName, this.departmentRent, this.departmentPhoneNumber);
  }
}

package model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "department")
public class Department {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "department_id")
  private Integer departmentId;

  @Column(name = "name")
  private String departmentName;

  @Column(name = "rent")
  private Integer departmentRent;

  @Column(name = "phone_number")
  private String departmentPhoneNumber;

  @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
  private Set<Employee> employees;

  public Department() {

  }

  public Department(String departmentName, Integer departmentRent, String departmentPhoneNumber) {
    this.departmentName = departmentName;
    this.departmentRent = departmentRent;
    this.departmentPhoneNumber = departmentPhoneNumber;
  }

  public Integer getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(Integer departmentId) {
    this.departmentId = departmentId;
  }

  public String getDepartmentName() {
    return departmentName;
  }

  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
  }

  public Integer getDepartmentRent() {
    return departmentRent;
  }

  public void setDepartmentRent(Integer departmentRent) {
    this.departmentRent = departmentRent;
  }

  public String getDepartmentPhoneNumber() {
    return departmentPhoneNumber;
  }

  public void setDepartmentPhoneNumber(String departmentPhoneNumber) {
    this.departmentPhoneNumber = departmentPhoneNumber;
  }

  public Set<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(Set<Employee> employees) {
    this.employees = employees;
  }

}

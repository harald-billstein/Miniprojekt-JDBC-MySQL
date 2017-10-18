package model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name = "employee")
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "employee_id")
  private int employeeId;

  @Column(name = "fname")
  private String firstName;

  @Column(name = "lname")
  private String lastName;

  @Column(name = "salary")
  private Integer salary;

  @Column(name = "hire_date")
  private Date hireDate;

  @Column(name = "department_id")
  private Integer departmentId;

  @JoinColumn(name = "department_id", insertable = false, updatable = false)
  @ManyToOne(optional = false)
  private Department department;

  @OneToOne(mappedBy = "employee")
  private CompanyCar companyCar;

  public Employee() {

  }

  public Employee(String firstName, String lastName, Integer salary, Date hireDate,
      Integer departmentId) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.salary = salary;
    this.hireDate = hireDate;
    this.departmentId = departmentId;
  }

  public int getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Integer getSalary() {
    return salary;
  }

  public void setSalary(Integer salary) {
    this.salary = salary;
  }

  public Date getHireDate() {
    return hireDate;
  }

  public void setHireDate(Date hireDate) {
    this.hireDate = hireDate;
  }

  public Integer getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(Integer departmentId) {
    this.departmentId = departmentId;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public CompanyCar getCompanyCar() {
    return companyCar;
  }

  public void setCompanyCar(CompanyCar companyCar) {
    this.companyCar = companyCar;
  }
  
}

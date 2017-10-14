package model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name = "employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employee_id;
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	private String fname;
	private String lname;
	private Integer salary;
	private Date hire_date;
	private Integer department_id;
	
	@JoinColumn(name = "department_id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private Department department;
	
	
	
	
	
	//TRY TO MAPP
//	@OneToOne(fetch=FetchType.EAGER, orphanRemoval = true)
//	@Fetch(FetchMode.JOIN)
//	@JoinColumn(name = "employee_id", insertable = false, updatable = false, 	unique = true, nullable = false)
//	private CompanyCar companyCar;
//	
//	public CompanyCar getCompanyCar() {
//		return companyCar;
//	}
//
//	public void setCompanyCar(CompanyCar companyCar) {
//		this.companyCar = companyCar;
//	}
	//TRY TO MAPP
	
	
	
	
	
	
	
	public Employee() {
		
	}
	


	public Employee(String fname, String lname, Integer salary, Date hire_date, Integer department_id) {
		this.fname = fname;
		this.lname = lname;
		this.salary = salary;
		this.hire_date = hire_date;
		this.department_id = department_id;	
	}
	
	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Date getHire_date() {
		return hire_date;
	}

	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}

	public Integer getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}
}

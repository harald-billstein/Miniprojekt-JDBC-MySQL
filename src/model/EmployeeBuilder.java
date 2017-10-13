package model;

import java.sql.Date;

public class EmployeeBuilder {
	private String fname;
	private String lname;
	private Integer salary;
	private Date hire_date;
	private Integer department_id;

	public EmployeeBuilder setFname(String fname) {
		this.fname = fname;
		return this;
	}

	public EmployeeBuilder setLname(String lname) {
		this.lname = lname;
		return this;
	}

	public EmployeeBuilder setSalary(Integer salary) {
		this.salary = salary;
		return this;
	}

	public EmployeeBuilder setHireDate(Date hire_date) {
		this.hire_date = hire_date;
		return this;
	}

	public EmployeeBuilder setDepartmentId(Integer department_id) {
		this.department_id = department_id;
		return this;
	}

	public Employee build() {
		return new Employee(this.fname, this.lname, this.salary, this.hire_date, this.department_id);
	}
}

package databasemodel;

import java.sql.Date;

public class EmployeeBuilder {
	private String fname;
	private String lname;
	private Integer salary;
	private Date hireDate;
	private Integer departmentId;

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

	public EmployeeBuilder setHireDate(Date hireDate) {
		this.hireDate = hireDate;
		return this;
	}

	public EmployeeBuilder setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
		return this;
	}

	public Employee build() {
		return new Employee(this.fname, this.lname, this.salary, this.hireDate, this.departmentId);
	}

	public class Employee {
		private Integer employeeId;
		private String fname;
		private String lname;
		private Integer salary;
		private Date hireDate;
		private Integer departmentId;

		public Employee(String fname, String lname, Integer salary, Date hireDate, Integer departmentId) {
			this.fname = fname;
			this.lname = lname;
			this.salary = salary;
			this.hireDate = hireDate;
			this.departmentId = departmentId;
		}

		public int getEmployeeId() {
			return employeeId;
		}

		public void setEmployeeId(Integer employeeId) {
			this.employeeId = employeeId;
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
	}
}

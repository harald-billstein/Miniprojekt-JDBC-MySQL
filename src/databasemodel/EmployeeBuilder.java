package databasemodel;

import java.sql.Date;

public class EmployeeBuilder {
	private int employeeId;
	private String fname;
	private String lname;
	private int salary;
	private Date hireDate;
	private int departmentId;

	public EmployeeBuilder setFname(String fname) {
		this.fname = fname;
		return this;
	}

	public EmployeeBuilder setLname(String lname) {
		this.lname = lname;
		return this;
	}

	public EmployeeBuilder salary(int salary) {
		this.salary = salary;
		return this;
	}

	public EmployeeBuilder setHireDate(Date hireDate) {
		this.hireDate = hireDate;
		return this;
	}

	public EmployeeBuilder departmentId(int departmentId) {
		this.departmentId = departmentId;
		return this;
	}

	public Employee build() {
		return new Employee(this.fname, this.lname, this.salary, this.hireDate, this.departmentId);
	}

	public class Employee {
		private int employeeId;
		private String fname;
		private String lname;
		private int salary;
		private Date hireDate;
		private int departmentId;

		public Employee(String fname, String lname, int salary, Date hireDate, int departmentId) {
			this.fname = fname;
			this.lname = lname;
			this.salary = salary;
			this.hireDate = hireDate;
			this.departmentId = departmentId;
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

		public int getSalary() {
			return salary;
		}

		public void setSalary(int salary) {
			this.salary = salary;
		}

		public Date getHireDate() {
			return hireDate;
		}

		public void setHireDate(Date hireDate) {
			this.hireDate = hireDate;
		}

		public int getDepartmentId() {
			return departmentId;
		}

		public void setDepartmentId(int departmentId) {
			this.departmentId = departmentId;
		}
	}
}

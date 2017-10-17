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
import javax.persistence.Transient;

@Entity(name = "employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employee_id;
	private String fname;
	private String lname;
	private Integer salary;
	private Date hire_date;
	private Integer department_id;
	
//	@Transient
//	private String departmentName;
//	@Transient
//	private Integer departmentRent;
//	@Transient
//	private String departmentPhoneNumber;
//	@Transient
//	private String reg_nr;
//	@Transient
//	private String brand;
//	@Transient
//	private String model;
//	@Transient
//	private Integer purchase_price;
//	@Transient
//	private Date purchase_date;

//	public String getReg_nr() {
//		return reg_nr;
//	}
//
//	public void setReg_nr(String reg_nr) {
//		this.reg_nr = reg_nr;
//	}
//
//	public String getBrand() {
//		return brand;
//	}
//
//	public void setBrand(String brand) {
//		this.brand = brand;
//	}
//
//	public String getModel() {
//		return model;
//	}
//
//	public void setModel(String model) {
//		this.model = model;
//	}
//
//	public Integer getPurchase_price() {
//		return purchase_price;
//	}
//
//	public void setPurchase_price(Integer purchase_price) {
//		this.purchase_price = purchase_price;
//	}
//
//	public Date getPurchase_date() {
//		return purchase_date;
//	}
//
//	public void setPurchase_date(Date purchase_date) {
//		this.purchase_date = purchase_date;
//	}

	@JoinColumn(name = "department_id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private Department department;
	
	@OneToOne(mappedBy = "employee")
	private CompanyCar companyCar;
	
	public Employee() {
		
	}

	public Employee(String fname, String lname, Integer salary, Date hire_date, Integer department_id) {
		this.fname = fname;
		this.lname = lname;
		this.salary = salary;
		this.hire_date = hire_date;
		this.department_id = department_id;	
	}
	
	public CompanyCar getCompanyCar() {
		return companyCar;
	}

	public void setCompanyCar(CompanyCar companyCar) {
		this.companyCar = companyCar;
	}
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
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
	
//	public String getDepartmentName() {
//		return departmentName;
//	}
//
//	public Integer getDepartmentRent() {
//		return departmentRent;
//	}
//
//	public void setDepartmentRent(Integer departmentRent) {
//		this.departmentRent = departmentRent;
//	}
//
//	public String getDepartmentPhoneNumber() {
//		return departmentPhoneNumber;
//	}
//
//	public void setDepartmentPhoneNumber(String departmentPhoneNumber) {
//		this.departmentPhoneNumber = departmentPhoneNumber;
//	}
//
//	public void setDepartmentName(String departmentName) {
//		this.departmentName = departmentName;
//	}
//	
//	public void setLocalVaraiblesFromSubclass() {
//		departmentName = department.getName();
//		departmentRent = department.getRent();
//		departmentPhoneNumber = department.getPhone_number();
//		reg_nr = companyCar.getReg_nr();
//		brand = companyCar.getBrand();
//		model = companyCar.getModel();
//		purchase_price = companyCar.getPurchase_price();
//		purchase_date = companyCar.getPurchase_date();
//	}
}

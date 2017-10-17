package model;

import java.sql.Date;

public class EmployeeObservable {
	
	private String firstName;
	private String lastName;
	private Integer salary;
	private Date hireDate;
	private String departmentName;
	private Integer departmentRent;
	private String departmentPhoneNumber; 
	private String companyCarRegNr;
	private String companyCarBrand;
	private String companyCarModel;
	private Integer companyCarPurchasePrise;
	private Date companyCarPurchaseDate;
	
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
	public String getCompanyCarRegNr() {
		return companyCarRegNr;
	}
	public void setCompanyCarRegNr(String companyCarRegNr) {
		this.companyCarRegNr = companyCarRegNr;
	}
	public String getCompanyCarBrand() {
		return companyCarBrand;
	}
	public void setCompanyCarBrand(String companyCarBrand) {
		this.companyCarBrand = companyCarBrand;
	}
	public String getCompanyCarModel() {
		return companyCarModel;
	}
	public void setCompanyCarModel(String companyCarModel) {
		this.companyCarModel = companyCarModel;
	}
	public Integer getCompanyCarPurchasePrise() {
		return companyCarPurchasePrise;
	}
	public void setCompanyCarPurchasePrise(Integer companyCarPurchasePrise) {
		this.companyCarPurchasePrise = companyCarPurchasePrise;
	}
	public Date getCompanyCarPurchaseDate() {
		return companyCarPurchaseDate;
	}
	public void setCompanyCarPurchaseDate(Date companyCarPurchaseDate) {
		this.companyCarPurchaseDate = companyCarPurchaseDate;
	}
	
	

}

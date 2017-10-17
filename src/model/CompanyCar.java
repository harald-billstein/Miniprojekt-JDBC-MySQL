package model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "company_car")
	public class CompanyCar {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private String reg_nr;
		private String brand;
		private String model;
		private Integer purchase_price;
		private Date purchase_date;
		private Integer employee_id;
		
		
		// TRY TO MAPP
		@OneToOne()
		@JoinColumn(name = "employee_id", insertable = false, updatable = false)
		private Employee employee;

		public Employee getEmployee() {
			return employee;
		}

		public void setEmployee(Employee employee) {
			this.employee = employee;
		}
		// TRY TO MAPP	

		public CompanyCar() {

		}
		
		public CompanyCar(String reg_nr, String brand, String model, Integer purchase_price, Date purchase_date,
				Integer employee_id) {
			super();
			this.reg_nr = reg_nr;
			this.brand = brand;
			this.model = model;
			this.purchase_price = purchase_price;
			this.purchase_date = purchase_date;
			this.employee_id = employee_id;
		}

		public String getReg_nr() {
			return reg_nr;
		}
		
		public void setReg_nr(String reg_nr) {
			this.reg_nr = reg_nr;
		}
		
		public String getBrand() {
			return brand;
		}
		
		public void setBrand(String brand) {
			this.brand = brand;
		}
		
		public String getModel() {
			return model;
		}
		
		public void setModel(String model) {
			this.model = model;
		}
		
		public Integer getPurchase_price() {
			return purchase_price;
		}
		
		public void setPurchase_price(Integer purchase_price) {
			this.purchase_price = purchase_price;
		}
		
		public Date getPurchase_date() {
			return purchase_date;
		}
		
		public void setPurchase_date(Date purchase_date) {
			this.purchase_date = purchase_date;
		}
		
		public Integer getEmployee_id() {
			return employee_id;
		}
		
		public void setEmployee_id(Integer employee_id) {
			this.employee_id = employee_id;
		}		
	}
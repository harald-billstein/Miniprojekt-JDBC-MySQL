package databasemodel;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
		
		public CompanyCar() {

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
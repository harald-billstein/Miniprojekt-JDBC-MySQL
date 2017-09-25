package databasemodel;

import java.sql.Date;

public class CompanyCarBuilder {
	
	private String reg_nr;
	private String brand;
	private String model;
	private int purchase_price;
	private Date purchase_date;
	private int employee_id;
	
	public CompanyCarBuilder setReg_nr(String reg_nr) {
		this.reg_nr = reg_nr;
		return this;
	}

	public CompanyCarBuilder setBrand(String brand) {
		this.brand = brand;
		return this;
	}

	public CompanyCarBuilder setModel(String model) {
		this.model = model;
		return this;
	}

	public CompanyCarBuilder setPurchase_price(int purchase_price) {
		this.purchase_price = purchase_price;
		return this;
	}

	public CompanyCarBuilder setPurchase_date(Date purchase_date) {
		this.purchase_date = purchase_date;
		return this;
	}

	public CompanyCarBuilder setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
		return this;
	}
	
	public CompanyCar build() {
		return new CompanyCar(this.reg_nr, this.brand, this.model, this.purchase_price, this.purchase_date, this.employee_id);
	}
	
	public class CompanyCar {
		
		private String reg_nr;
		private String brand;
		private String model;
		private int purchase_price;
		private Date purchase_date;
		private int employee_id;
		
		public CompanyCar(String reg_nr, String brand, String model , int purchase_price, Date purchase_date,int employee_id) {
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
		
		public int getPurchase_price() {
			return purchase_price;
		}
		
		public void setPurchase_price(int purchase_price) {
			this.purchase_price = purchase_price;
		}
		
		public Date getPurchase_date() {
			return purchase_date;
		}
		
		public void setPurchase_date(Date purchase_date) {
			this.purchase_date = purchase_date;
		}
		
		public int getEmployee_id() {
			return employee_id;
		}
		
		public void setEmployee_id(int employee_id) {
			this.employee_id = employee_id;
		}		
	}

}

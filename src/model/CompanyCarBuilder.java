package model;

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
}
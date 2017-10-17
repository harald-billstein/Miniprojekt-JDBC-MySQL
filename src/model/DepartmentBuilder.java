package model;

public class DepartmentBuilder {

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRent() {
		return rent;
	}

	public void setRent(int rent) {
		this.rent = rent;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	private String name;
	private int rent;
	private String phone_number;
	
	public Department Build() {
		return new Department(this.name, this.rent, this.phone_number);
	}
}

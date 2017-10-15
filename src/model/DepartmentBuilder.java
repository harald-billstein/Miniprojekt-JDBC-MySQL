package model;

//import lombok.Setter;
//
//@Setter
public class DepartmentBuilder {

	private String name;
	private int rent;

	public void setName(String name) {
		this.name = name;
	}

	public void setRent(int rent) {
		this.rent = rent;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	private String phone_number;
	
	public Department Build() {
		return new Department(this.name, this.rent, this.phone_number);
	}
}

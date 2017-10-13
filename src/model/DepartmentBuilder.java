package model;

import lombok.Setter;

@Setter
public class DepartmentBuilder {

	private String name;
	private int rent;
	private String phone_number;
	
	public Department Build() {
		return new Department(this.name, this.rent, this.phone_number);
	}
}

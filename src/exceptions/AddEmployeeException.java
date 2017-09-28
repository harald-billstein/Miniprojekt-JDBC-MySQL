package exceptions;

public class AddEmployeeException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5754329735416692678L;

	public AddEmployeeException(Exception e) {
		System.out.println("Error adding employee, department not found " + e.getMessage());
	}
}

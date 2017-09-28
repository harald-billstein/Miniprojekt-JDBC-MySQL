package exceptions;

public class UpdateSalaryException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8809470702241160742L;
	
	public UpdateSalaryException(Exception e) {
		System.out.println("Error updating salary: Employee ID not found");
	}

}

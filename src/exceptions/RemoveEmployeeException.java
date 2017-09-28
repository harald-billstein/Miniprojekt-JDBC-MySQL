package exceptions;

public class RemoveEmployeeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1991336762921277067L;
	
	public RemoveEmployeeException(Exception e) {
		System.out.println("Employee ID not found: " + e.getMessage());
	}

}

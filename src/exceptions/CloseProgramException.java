package exceptions;

public class CloseProgramException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9094981296626025997L;
	
	public CloseProgramException(Exception e) {
		System.out.println("Close program exception " + e.getMessage());
	}
}

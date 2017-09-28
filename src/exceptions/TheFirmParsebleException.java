package exceptions;

public class TheFirmParsebleException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4436035250247497190L;

	public TheFirmParsebleException(Exception e) {
		System.out.println("Error parsing string: " + e.getMessage());
	}

}

package exceptions;

public class PrintException extends Exception {
	
	private static final long serialVersionUID = 7848744324819869991L;

	public PrintException(Exception e) {
		System.out.println("Print exception " + e.getMessage());
	}

}

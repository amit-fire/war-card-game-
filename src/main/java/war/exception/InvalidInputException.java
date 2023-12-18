package war.exception;

public class InvalidInputException extends Exception {

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		// TODO: externalize
		return "The input is not a natural number";
	}

}

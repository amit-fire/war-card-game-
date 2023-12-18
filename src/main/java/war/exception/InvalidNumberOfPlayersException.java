package war.exception;

public class InvalidNumberOfPlayersException extends InvalidInputException {

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		// TODO: externalize
		return "The input is not a natural number larger than 1";
	}

}

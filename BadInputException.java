package assign4;

/**
 * This class sends custom messages on bad inputs.
 * @author fhj
 */
public class BadInputException extends Exception {

	/**
	 * Constructor, allows custom message assignment for thrown exception.
	 * @param message
	 */
	public BadInputException(String message) {
        super(message);
	}

}
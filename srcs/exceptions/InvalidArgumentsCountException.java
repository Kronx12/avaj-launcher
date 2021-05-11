package srcs.exceptions;

@SuppressWarnings("serial")
public class InvalidArgumentsCountException extends Exception {
	public InvalidArgumentsCountException(String errorMessage) {
		super(errorMessage);
	}
}

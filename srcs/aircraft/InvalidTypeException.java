package aircraft;

public class InvalidTypeException extends Exception {
    public InvalidTypeException(String errorMessage) {
        super(errorMessage);
    }
}
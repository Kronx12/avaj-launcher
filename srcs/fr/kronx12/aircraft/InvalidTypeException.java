package fr.kronx12.aircraft;

public class InvalidTypeException extends Exception {
    public InvalidTypeException(String errorMessage) {
        super(errorMessage);
    }
}
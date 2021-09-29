package fr.kronx12.aircraft;

public class InvalidCoordinatesException extends Exception {
    public InvalidCoordinatesException(String errorMessage) {
        super(errorMessage);
    }
}
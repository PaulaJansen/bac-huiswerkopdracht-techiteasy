package nl.novi.homework.techiteasy.exception;

public class NameTooLongException extends RuntimeException {
    public NameTooLongException(String message) {
        super(message);
    }
}

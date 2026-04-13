package uz.iaiiai.banking.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("User already registered");
    }
}

package uz.iaiiai.banking.exception;

public class UserFoundException extends RuntimeException {
    public UserFoundException() {
        super("User already registered");
    }
}

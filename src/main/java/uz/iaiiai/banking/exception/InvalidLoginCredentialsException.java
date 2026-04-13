package uz.iaiiai.banking.exception;

public class InvalidLoginCredentialsException extends RuntimeException {
    public InvalidLoginCredentialsException() {
        super("Invalid login credentials");
    }
}

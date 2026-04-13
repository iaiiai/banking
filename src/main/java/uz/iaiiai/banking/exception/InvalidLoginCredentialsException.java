package uz.iaiiai.banking.exception;

public class InvalidLoginCredentials extends RuntimeException {
    public InvalidLoginCredentials() {
        super("Invalid login credentials");
    }
}

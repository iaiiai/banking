package uz.iaiiai.banking.exception;

public class InvalidTransferRecipientException extends RuntimeException {
    public InvalidTransferRecipientException() {
        super("Invalid transfer recipient");
    }
}

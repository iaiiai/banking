package uz.iaiiai.banking.exception;

public class RecipientNotFoundException extends RuntimeException {
    public RecipientNotFoundException() {
        super("Recipient not found");
    }
}

package uz.iaiiai.banking.exception;

public class PayableNotFoundException extends RuntimeException {
    public PayableNotFoundException() {
        super("Payable with such alias not found");
    }
}

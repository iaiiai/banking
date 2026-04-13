package uz.iaiiai.banking.exception;

public class PayableAliasTakenException extends RuntimeException {
    public PayableAliasTakenException() {
        super("Payable alias has been already taken");
    }
}

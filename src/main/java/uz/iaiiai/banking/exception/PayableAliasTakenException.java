package uz.iaiiai.banking.exception;

public class PayableAliasTaken extends RuntimeException {
    public PayableAliasTaken() {
        super("Payable alias has been already taken");
    }
}

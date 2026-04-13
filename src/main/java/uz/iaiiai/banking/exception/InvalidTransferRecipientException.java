package uz.iaiiai.banking.exception;

public class InvalidTransferRecieverException extends RuntimeException {
    public InvalidTransferRecieverException() {
        super("Invalid transfer receiver");
    }
}

package uz.iaiiai.banking.exception;

public class PayableNotFound extends RuntimeException {
  public PayableNotFound(String message) {
    super(message);
  }
}

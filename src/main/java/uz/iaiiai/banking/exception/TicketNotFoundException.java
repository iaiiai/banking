package uz.iaiiai.banking.exception;

public class TicketNotFoundException extends RuntimeException {
  public TicketNotFoundException(String message) {
    super(message);
  }
}

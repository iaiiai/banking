package uz.iaiiai.banking.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.iaiiai.banking.dto.response.ApiErrorResponseDto;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponseDto> handleUserAlreadyExists(
            UserAlreadyExistsException ex,
            HttpServletRequest request
    ) {
        ApiErrorResponseDto error = new ApiErrorResponseDto(
                LocalDateTime.now().toString(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiErrorResponseDto> handleUserNotFound(
            UserNotFoundException ex,
            HttpServletRequest request
    ) {
        ApiErrorResponseDto error = new ApiErrorResponseDto(
                LocalDateTime.now().toString(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ApiErrorResponseDto> handleAuthorizationDenied(
            AuthorizationDeniedException ex,
            HttpServletRequest request
    ) {
        ApiErrorResponseDto error = new ApiErrorResponseDto(
                LocalDateTime.now().toString(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InvalidTransferRecipientException.class)
    public ResponseEntity<ApiErrorResponseDto> handleInvalidTransferReceiver(
            InvalidTransferRecipientException ex,
            HttpServletRequest request
    ) {
        ApiErrorResponseDto error = new ApiErrorResponseDto(
                LocalDateTime.now().toString(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecipientNotFoundException.class)
    public ResponseEntity<ApiErrorResponseDto> handleRecipientNotFound(
            RecipientNotFoundException ex,
            HttpServletRequest request
    ) {
        ApiErrorResponseDto error = new ApiErrorResponseDto(
                LocalDateTime.now().toString(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidLoginCredentialsException.class)
    public ResponseEntity<ApiErrorResponseDto> handleInvalidLoginCredentials(
            InvalidLoginCredentialsException ex,
            HttpServletRequest request
    ) {
        ApiErrorResponseDto error = new ApiErrorResponseDto(
                LocalDateTime.now().toString(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ApiErrorResponseDto> handleInsufficientBalance(
            InsufficientBalanceException ex,
            HttpServletRequest request
    ) {
        ApiErrorResponseDto error = new ApiErrorResponseDto(
                LocalDateTime.now().toString(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PayableAliasTakenException.class)
    public ResponseEntity<ApiErrorResponseDto> handlePayableAliasTaken  (
            PayableAliasTakenException ex,
            HttpServletRequest request
    ) {
        ApiErrorResponseDto error = new ApiErrorResponseDto(
                LocalDateTime.now().toString(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PayableNotFoundException.class)
    public ResponseEntity<ApiErrorResponseDto> handlePayableNotFound(
            PayableNotFoundException ex,
            HttpServletRequest request
    ) {
        ApiErrorResponseDto error = new ApiErrorResponseDto(
                LocalDateTime.now().toString(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiErrorResponseDto> handleRuntime(
            RuntimeException ex,
            HttpServletRequest request
    ) {
        log.error("Unhandled exception at [{} {}]",
                request.getMethod(),
                request.getRequestURI(),
                ex);
        ApiErrorResponseDto error = new ApiErrorResponseDto(
                LocalDateTime.now().toString(),
                "Internal Server Error",
                request.getRequestURI()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

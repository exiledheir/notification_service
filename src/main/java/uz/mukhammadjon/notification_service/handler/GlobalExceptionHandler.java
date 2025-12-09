package uz.mukhammadjon.notification_service.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.mukhammadjon.notification_service.dto.response.NotificationServiceResponse;
import uz.mukhammadjon.notification_service.exception.DataExistsException;
import uz.mukhammadjon.notification_service.exception.LoginNotFoundException;
import uz.mukhammadjon.notification_service.exception.MerchantNotFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataExistsException.class)
    public ResponseEntity<NotificationServiceResponse<Void>> handleDataExists(DataExistsException ex) {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(NotificationServiceResponse.<Void>builder()
                .success(false)
                .message(ex.getMessage())
                .payload(null)
                .build());
    }

    @ExceptionHandler(MerchantNotFoundException.class)
    public ResponseEntity<NotificationServiceResponse<Void>> handleMerchantNotFound(MerchantNotFoundException ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(NotificationServiceResponse.<Void>builder()
                .success(false)
                .message(ex.getMessage())
                .payload(null)
                .build());
    }

    @ExceptionHandler(LoginNotFoundException.class)
    public ResponseEntity<NotificationServiceResponse<Void>> handleLoginNotFound(LoginNotFoundException ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(NotificationServiceResponse.<Void>builder()
                .success(false)
                .message(ex.getMessage())
                .payload(null)
                .build());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
        MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}

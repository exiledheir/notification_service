package uz.mukhammadjon.notification_service.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.mukhammadjon.notification_service.dto.response.NotificationServiceResponse;
import uz.mukhammadjon.notification_service.enums.ErrorCode;
import uz.mukhammadjon.notification_service.exception.DataExistsException;
import uz.mukhammadjon.notification_service.exception.LoginNotFoundException;
import uz.mukhammadjon.notification_service.exception.MerchantNotFoundException;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataExistsException.class)
    public ResponseEntity<NotificationServiceResponse<Void>> handleDataExists(DataExistsException ex) {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(NotificationServiceResponse.error(
                ErrorCode.DATA_EXISTS.getCode(),
                ErrorCode.DATA_EXISTS.getMessage(),
                List.of(ex.getMessage())
            ));
    }

    @ExceptionHandler(MerchantNotFoundException.class)
    public ResponseEntity<NotificationServiceResponse<Void>> handleMerchantNotFound(MerchantNotFoundException ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(NotificationServiceResponse.error(
                ErrorCode.MERCHANT_NOT_FOUND.getCode(),
                ErrorCode.MERCHANT_NOT_FOUND.getMessage(),
                List.of(ex.getMessage())
            ));
    }

    @ExceptionHandler(LoginNotFoundException.class)
    public ResponseEntity<NotificationServiceResponse<Void>> handleLoginNotFound(LoginNotFoundException ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(NotificationServiceResponse.error(
                ErrorCode.LOGIN_NOT_FOUND.getCode(),
                ErrorCode.LOGIN_NOT_FOUND.getMessage(),
                List.of(ex.getMessage())
            ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<NotificationServiceResponse<Void>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> details = ex.getBindingResult().getAllErrors().stream()
            .map(error -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                return fieldName + ": " + errorMessage;
            })
            .toList();

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(NotificationServiceResponse.error(
                ErrorCode.VALIDATION_ERROR.getCode(),
                ErrorCode.VALIDATION_ERROR.getMessage(),
                details
            ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<NotificationServiceResponse<Void>> handleGenericException(Exception ex) {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(NotificationServiceResponse.error(
                ErrorCode.INTERNAL_ERROR.getCode(),
                ErrorCode.INTERNAL_ERROR.getMessage(),
                List.of(ex.getMessage())
            ));
    }
}
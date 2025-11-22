package uz.mukhammadjon.notification_service.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.mukhammadjon.notification_service.dto.response.NotificationServiceResponse;
import uz.mukhammadjon.notification_service.exception.DataExistsException;

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
}

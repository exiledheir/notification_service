package uz.mukhammadjon.notification_service.exception;

public class DataExistsException extends RuntimeException {
    public DataExistsException(String message) {
        super(message);
    }
}

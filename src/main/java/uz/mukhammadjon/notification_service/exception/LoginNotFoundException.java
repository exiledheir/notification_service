package uz.mukhammadjon.notification_service.exception;

public class LoginNotFoundException extends RuntimeException {
    public LoginNotFoundException(String message) {
        super(message);
    }
}

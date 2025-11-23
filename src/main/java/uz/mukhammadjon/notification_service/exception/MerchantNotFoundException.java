package uz.mukhammadjon.notification_service.exception;

public class MerchantNotFoundException extends RuntimeException {
    public MerchantNotFoundException(String message) {
        super(message);
    }
}

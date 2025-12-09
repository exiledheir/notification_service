package uz.mukhammadjon.notification_service.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    MERCHANT_NOT_FOUND("MERCHANT_NOT_FOUND", "Merchant not found"),
    LOGIN_NOT_FOUND("LOGIN_NOT_FOUND", "Login not found"),
    DATA_EXISTS("DATA_EXISTS", "Data already exists"),
    VALIDATION_ERROR("VALIDATION_ERROR", "Validation failed"),
    INVALID_RECEIVER("INVALID_RECEIVER", "Invalid receiver for notification type"),
    INTERNAL_ERROR("INTERNAL_ERROR", "Internal server error");

    private final String code;
    private final String message;
}
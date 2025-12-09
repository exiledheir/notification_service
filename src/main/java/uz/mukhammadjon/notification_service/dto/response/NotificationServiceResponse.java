package uz.mukhammadjon.notification_service.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationServiceResponse<P> {

    P data;
    ErrorDetails error;

    public static <T> NotificationServiceResponse<T> success(T data) {
        return NotificationServiceResponse.<T>builder().data(data).build();
    }

    public static <T> NotificationServiceResponse<T> error(String code, String message, List<String> details) {
        return NotificationServiceResponse.<T>builder().error(ErrorDetails.builder().code(code).message(message).details(details).build()).build();
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class ErrorDetails {
        String code;
        String message;
        List<String> details;
    }
}

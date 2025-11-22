package uz.mukhammadjon.notification_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationServiceResponse<P> {
    private String message;
    private P payload;
    private boolean success;

    public static <T> NotificationServiceResponse<T> success(T payload) {
        return NotificationServiceResponse.<T>builder().message("Success").payload(payload).success(true).build();
    }
}

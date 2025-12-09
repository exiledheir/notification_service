package uz.mukhammadjon.notification_service.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationServiceResponse<P> {

    String message;
    P payload;
    boolean success;

    public static <T> NotificationServiceResponse<T> success(T payload) {
        return NotificationServiceResponse.<T>builder().message("Success").payload(payload).success(true).build();
    }
}

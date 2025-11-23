package uz.mukhammadjon.notification_service.dto.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class NotificationResponse {
    private Long notificationId;
}

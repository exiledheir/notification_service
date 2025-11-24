package uz.mukhammadjon.notification_service.dto.notification.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationEvent {
    private Long notificationId;
    private String content;
    private String receiver;
    private Long merchantId;
    private String type;
    private String webhookUrl;
}

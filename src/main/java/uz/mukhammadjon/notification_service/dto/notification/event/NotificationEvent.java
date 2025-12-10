package uz.mukhammadjon.notification_service.dto.notification.event;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import uz.mukhammadjon.notification_service.constant.enums.Type;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationEvent {

    Long notificationId;
    String content;
    String receiver;
    Long merchantId;
    Type type;
    String webhookUrl;
}
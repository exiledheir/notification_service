package uz.mukhammadjon.notification_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import uz.mukhammadjon.notification_service.dto.notification.NotificationRequest;
import uz.mukhammadjon.notification_service.dto.notification.NotificationResponse;
import uz.mukhammadjon.notification_service.dto.notification.Receiver;
import uz.mukhammadjon.notification_service.dto.notification.event.NotificationEvent;
import uz.mukhammadjon.notification_service.entity.Notification;
import uz.mukhammadjon.notification_service.constant.enums.Type;

@Mapper
public interface NotificationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "merchant", ignore = true)
    @Mapping(target = "receiver", source = "request", qualifiedByName = "extractReceiver")
    Notification toEntity(NotificationRequest request);

    @Mapping(target = "notificationId", source = "id")
    NotificationResponse toResponse(Notification notification);

    @Mapping(target = "notificationId", source = "id")
    @Mapping(target = "merchantId", source = "merchant.id")
    @Mapping(target = "webhookUrl", source = "merchant.webhook")
    NotificationEvent toEvent(Notification notification);

    @Named("extractReceiver")
    default String extractReceiver(NotificationRequest request) {
        Receiver receiver = request.getReceiver();
        Type type = request.getType();

        return switch (type) {
            case SMS -> receiver.getPhone();
            case EMAIL -> receiver.getEmail();
            case PUSH -> receiver.getFirebaseToken();
        };
    }
}

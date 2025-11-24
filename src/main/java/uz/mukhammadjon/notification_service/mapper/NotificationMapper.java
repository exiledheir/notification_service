package uz.mukhammadjon.notification_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uz.mukhammadjon.notification_service.dto.notification.NotificationEmailRequest;
import uz.mukhammadjon.notification_service.dto.notification.NotificationResponse;
import uz.mukhammadjon.notification_service.dto.notification.NotificationSmsRequest;
import uz.mukhammadjon.notification_service.entity.Notification;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NotificationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "merchant", ignore = true)
    Notification fromSmsToEntity(NotificationSmsRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "merchant", ignore = true)
    Notification fromEmailToEntity(NotificationEmailRequest request);

    @Mapping(target = "notificationId", source = "id")
    NotificationResponse toResponse(Notification notification);
}

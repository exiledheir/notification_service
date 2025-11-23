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

    //    id         bigint generated always as identity primary key, ----------
//    status     stats        not null default 'CREATED', default created
//    created_at timestamp not null default current_timestamp, -------
//    updated_at timestamp not null default current_timestamp,-------
//    content    varchar not null,--------
//    merchant_id bigint       not null,---------
//    receiver   varchar(50)  not null,---------
//    constraint fk_merchant foreign key (merchant_id) references merchants (id) on delete cascade

    /// /    "merchant": 1,  only numbers -->>merchant_id
    /// /    "content": "", size <=250 ----->content
    /// /    "receiver": "" regex 998(90, 91, 93, 97) 3332211 -----> receiver
    /// /}
    /// /
    /// /response-body: {
    /// /    "notificationId": 1
    /// /}
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

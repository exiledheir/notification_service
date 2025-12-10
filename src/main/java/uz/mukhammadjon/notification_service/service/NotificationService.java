package uz.mukhammadjon.notification_service.service;

import uz.mukhammadjon.notification_service.constant.enums.Status;
import uz.mukhammadjon.notification_service.dto.notification.NotificationRequest;
import uz.mukhammadjon.notification_service.dto.notification.NotificationResponse;

public interface NotificationService {

    NotificationResponse sendNotification(NotificationRequest request);

    void updateStatus(Long notificationId, Status status);

}

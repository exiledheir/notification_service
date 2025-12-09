package uz.mukhammadjon.notification_service.service;

import uz.mukhammadjon.notification_service.dto.notification.NotificationRequest;
import uz.mukhammadjon.notification_service.dto.notification.NotificationResponse;

public interface NotificationService {

    NotificationResponse sendNotification(NotificationRequest request);

}

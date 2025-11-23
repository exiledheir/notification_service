package uz.mukhammadjon.notification_service.service;

import uz.mukhammadjon.notification_service.dto.notification.NotificationEmailRequest;
import uz.mukhammadjon.notification_service.dto.notification.NotificationSmsRequest;
import uz.mukhammadjon.notification_service.dto.notification.NotificationResponse;

public interface NotificationService {
    NotificationResponse sendSms(NotificationSmsRequest request);
    NotificationResponse sendEmail(NotificationEmailRequest request);
}

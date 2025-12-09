package uz.mukhammadjon.notification_service.service;

import uz.mukhammadjon.notification_service.dto.notification.NotificationEmailRequest;
import uz.mukhammadjon.notification_service.dto.notification.NotificationResponse;
import uz.mukhammadjon.notification_service.dto.notification.NotificationSmsRequest;

public interface NotificationService {

    NotificationResponse sendSms(NotificationSmsRequest request);

    NotificationResponse sendEmail(NotificationEmailRequest request);
}

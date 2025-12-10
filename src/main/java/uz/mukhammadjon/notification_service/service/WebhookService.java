package uz.mukhammadjon.notification_service.service;

import uz.mukhammadjon.notification_service.entity.Notification;

public interface WebhookService {

    void sendWebhook(Notification notification);
}
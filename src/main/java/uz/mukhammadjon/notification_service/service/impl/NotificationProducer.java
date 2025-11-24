package uz.mukhammadjon.notification_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import uz.mukhammadjon.notification_service.dto.notification.event.NotificationEvent;

@Service
@RequiredArgsConstructor
public class NotificationProducer {
    private final KafkaTemplate<String, NotificationEvent> kafkaTemplate;

    public void sendSmsNotification(NotificationEvent event) {
        kafkaTemplate.send("sms-notifications", String.valueOf(event.getNotificationId()), event);
    }

    public void sendEmailNotification(NotificationEvent event) {
        kafkaTemplate.send("email-notifications", String.valueOf(event.getNotificationId()), event);
    }
}

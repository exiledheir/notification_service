package uz.mukhammadjon.notification_service.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.mukhammadjon.notification_service.dto.notification.event.NotificationEvent;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class NotificationProducer {

    KafkaTemplate<String, NotificationEvent> kafkaTemplate;

    @Transactional
    public void sendSmsNotification(NotificationEvent event) {
        kafkaTemplate.send("sms-notifications", String.valueOf(event.getNotificationId()), event);
    }

    @Transactional
    public void sendEmailNotification(NotificationEvent event) {
        kafkaTemplate.send("email-notifications", String.valueOf(event.getNotificationId()), event);
    }
}

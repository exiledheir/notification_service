package uz.mukhammadjon.notification_service.component.kafka.producer;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import uz.mukhammadjon.notification_service.configuration.props.KafkaProps;
import uz.mukhammadjon.notification_service.dto.notification.event.NotificationEvent;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class NotificationProducer {

    KafkaTemplate<String, NotificationEvent> notificationKafkaTemplate;
    KafkaProps kafkaProps;

    public void send(NotificationEvent event) {
        log.debug("Notification sending: {}", event);
        notificationKafkaTemplate.send(
            kafkaProps.getTopics().getNotification(),
            String.valueOf(event.getNotificationId()),
            event
        );
    }
}
package uz.mukhammadjon.notification_service.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import uz.mukhammadjon.notification_service.dto.webhook.WebhookPayload;
import uz.mukhammadjon.notification_service.entity.Notification;
import uz.mukhammadjon.notification_service.service.WebhookService;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WebhookServiceImpl implements WebhookService {

    RestClient restClient;

    @Override
    public void sendWebhook(Notification notification) {
        String webhookUrl = notification.getMerchant().getWebhook();

        WebhookPayload payload = WebhookPayload.builder()
            .notificationId(notification.getId())
            .status(notification.getStatus().name())
            .type(notification.getType().name())
            .receiver(notification.getReceiver())
            .build();
        restClient.post()
            .uri(webhookUrl)
            .body(payload)
            .retrieve()
            .toBodilessEntity();
    }
}
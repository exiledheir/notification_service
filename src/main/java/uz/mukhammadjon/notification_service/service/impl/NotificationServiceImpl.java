package uz.mukhammadjon.notification_service.service.impl;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.mukhammadjon.notification_service.component.kafka.producer.NotificationProducer;
import uz.mukhammadjon.notification_service.constant.enums.Status;
import uz.mukhammadjon.notification_service.dto.notification.NotificationRequest;
import uz.mukhammadjon.notification_service.dto.notification.NotificationResponse;
import uz.mukhammadjon.notification_service.dto.notification.event.NotificationEvent;
import uz.mukhammadjon.notification_service.entity.Merchant;
import uz.mukhammadjon.notification_service.entity.Notification;
import uz.mukhammadjon.notification_service.exception.MerchantNotFoundException;
import uz.mukhammadjon.notification_service.mapper.NotificationMapper;
import uz.mukhammadjon.notification_service.repository.MerchantRepository;
import uz.mukhammadjon.notification_service.repository.NotificationRepository;
import uz.mukhammadjon.notification_service.service.NotificationService;
import uz.mukhammadjon.notification_service.service.WebhookService;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class NotificationServiceImpl implements NotificationService {

    NotificationRepository repository;
    NotificationMapper notificationMapper;
    MerchantRepository merchantRepository;
    NotificationProducer notificationProducer;
    private final WebhookService webhookService;

    @Override
    @Transactional
    public NotificationResponse sendNotification(NotificationRequest request) {
        Merchant merchant = getAuthenticatedMerchant();

        Notification notification = notificationMapper.toEntity(request);
        notification.setMerchant(merchant);
        notification.setType(request.getType());

        notification = repository.save(notification);

        NotificationEvent event = notificationMapper.toEvent(notification);
        notificationProducer.send(event);

        return notificationMapper.toResponse(notification);
    }

    @Override
    @Transactional
    public void updateStatus(Long notificationId, Status status) {
        Notification notification = repository.findById(notificationId)
            .orElseThrow(() -> new RuntimeException("Notification not found: " + notificationId));

        notification.setStatus(status);
        repository.save(notification);

        if (status == Status.SENT || status == Status.FAILED) {
            webhookService.sendWebhook(notification);
        }
    }

    private Merchant getAuthenticatedMerchant() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();

        return merchantRepository.findByLogin(login)
            .orElseThrow(() -> new MerchantNotFoundException("Merchant not found: " + login));
    }
}

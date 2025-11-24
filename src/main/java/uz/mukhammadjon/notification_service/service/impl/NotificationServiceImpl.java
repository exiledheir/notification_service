package uz.mukhammadjon.notification_service.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.mukhammadjon.notification_service.dto.notification.NotificationEmailRequest;
import uz.mukhammadjon.notification_service.dto.notification.NotificationResponse;
import uz.mukhammadjon.notification_service.dto.notification.NotificationSmsRequest;
import uz.mukhammadjon.notification_service.dto.notification.event.NotificationEvent;
import uz.mukhammadjon.notification_service.entity.Merchant;
import uz.mukhammadjon.notification_service.entity.Notification;
import uz.mukhammadjon.notification_service.exception.MerchantNotFoundException;
import uz.mukhammadjon.notification_service.mapper.NotificationMapper;
import uz.mukhammadjon.notification_service.repository.MerchantRepository;
import uz.mukhammadjon.notification_service.repository.NotificationRepository;
import uz.mukhammadjon.notification_service.service.NotificationService;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repository;
    private final NotificationMapper notificationMapper;
    private final MerchantRepository merchantRepository;
    private final NotificationProducer notificationProducer;

    @Override
    @Transactional
    public NotificationResponse sendSms(NotificationSmsRequest request) {
        Merchant merchant = getAuthenticatedMerchant();

        Notification notification = notificationMapper.fromSmsToEntity(request);
        notification.setMerchant(merchant);
        notification = repository.save(notification);

        NotificationEvent event = notificationMapper.toEvent(notification);
        event.setType("SMS");
        notificationProducer.sendSmsNotification(event);

        return notificationMapper.toResponse(notification);
    }

    @Override
    @Transactional
    public NotificationResponse sendEmail(NotificationEmailRequest request) {
        Merchant merchant = getAuthenticatedMerchant();

        Notification notification = notificationMapper.fromEmailToEntity(request);
        notification.setMerchant(merchant);
        notification = repository.save(notification);

        NotificationEvent event = notificationMapper.toEvent(notification);
        event.setType("EMAIL");

        notificationProducer.sendEmailNotification(event);

        return notificationMapper.toResponse(notification);
    }

    private Merchant getAuthenticatedMerchant() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();

        return merchantRepository.findByLogin(login)
            .orElseThrow(() -> new MerchantNotFoundException("Merchant not found: " + login));
    }
}

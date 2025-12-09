package uz.mukhammadjon.notification_service.service.impl;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.mukhammadjon.notification_service.dto.notification.NotificationRequest;
import uz.mukhammadjon.notification_service.dto.notification.NotificationResponse;
import uz.mukhammadjon.notification_service.entity.Merchant;
import uz.mukhammadjon.notification_service.entity.Notification;
import uz.mukhammadjon.notification_service.exception.MerchantNotFoundException;
import uz.mukhammadjon.notification_service.mapper.NotificationMapper;
import uz.mukhammadjon.notification_service.repository.MerchantRepository;
import uz.mukhammadjon.notification_service.repository.NotificationRepository;
import uz.mukhammadjon.notification_service.service.NotificationService;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class NotificationServiceImpl implements NotificationService {

    NotificationRepository repository;
    NotificationMapper notificationMapper;
    MerchantRepository merchantRepository;

    @Override
    @Transactional
    public NotificationResponse sendNotification(NotificationRequest request) {
        Merchant merchant = getAuthenticatedMerchant();

        Notification notification = notificationMapper.toEntity(request);
        notification.setMerchant(merchant);
        notification.setType(request.getType());

        notification = repository.save(notification);

        return notificationMapper.toResponse(notification);
    }
    private Merchant getAuthenticatedMerchant() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();

        return merchantRepository.findByLogin(login)
            .orElseThrow(() -> new MerchantNotFoundException("Merchant not found: " + login));
    }
}

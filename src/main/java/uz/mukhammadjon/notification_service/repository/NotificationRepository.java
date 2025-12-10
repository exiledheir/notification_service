package uz.mukhammadjon.notification_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mukhammadjon.notification_service.constant.enums.Status;
import uz.mukhammadjon.notification_service.entity.Notification;

import java.time.LocalDateTime;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    long countByMerchantIdAndStatusAndCreatedAtBetween(Long merchantId, Status status, LocalDateTime start, LocalDateTime end);
}

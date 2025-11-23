package uz.mukhammadjon.notification_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mukhammadjon.notification_service.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}

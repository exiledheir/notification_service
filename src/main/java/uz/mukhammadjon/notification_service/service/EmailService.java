package uz.mukhammadjon.notification_service.service;

import uz.mukhammadjon.notification_service.entity.Merchant;

import java.math.BigDecimal;
import java.time.YearMonth;

public interface EmailService {

    void sendInvoice(Merchant merchant, long notificationCount, BigDecimal totalAmount, YearMonth period);
}
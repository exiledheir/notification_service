package uz.mukhammadjon.notification_service.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import uz.mukhammadjon.notification_service.entity.Merchant;
import uz.mukhammadjon.notification_service.service.EmailService;

import java.math.BigDecimal;
import java.time.YearMonth;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailServiceImpl implements EmailService {

    JavaMailSender mailSender;

    @Override
    public void sendInvoice(Merchant merchant, long notificationCount, BigDecimal totalAmount, YearMonth period) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(merchant.getEmail());
        message.setSubject("Invoice - " + period);
        message.setText("Notifications: " + notificationCount + "\nTotal: " + totalAmount);

        mailSender.send(message);
    }
}
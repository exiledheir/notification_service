package uz.mukhammadjon.notification_service.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.mukhammadjon.notification_service.constant.enums.Status;
import uz.mukhammadjon.notification_service.entity.Merchant;
import uz.mukhammadjon.notification_service.entity.Price;
import uz.mukhammadjon.notification_service.repository.MerchantRepository;
import uz.mukhammadjon.notification_service.repository.NotificationRepository;
import uz.mukhammadjon.notification_service.repository.PriceRepository;
import uz.mukhammadjon.notification_service.service.EmailService;
import uz.mukhammadjon.notification_service.service.InvoiceService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InvoiceServiceImpl implements InvoiceService {

    MerchantRepository merchantRepository;
    NotificationRepository notificationRepository;
    PriceRepository priceRepository;
    EmailService emailService;

    @Override
    public void generateAndSendInvoices() {
        List<Merchant> merchants = merchantRepository.findAll();
        BigDecimal currentPrice = getActivePrice();

        YearMonth currentMonth = YearMonth.now();
        LocalDateTime startOfMonth = currentMonth.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = currentMonth.atEndOfMonth().atTime(23, 59, 59);

        for (Merchant merchant : merchants) {
            long sentCount = notificationRepository.countByMerchantIdAndStatusAndCreatedAtBetween(
                merchant.getId(),
                Status.SENT,
                startOfMonth,
                endOfMonth
            );
            if (sentCount > 0) {
                BigDecimal totalAmount = currentPrice.multiply(BigDecimal.valueOf(sentCount));
                emailService.sendInvoice(merchant, sentCount, totalAmount, currentMonth);
            }
        }
    }

    private BigDecimal getActivePrice() {
        return priceRepository.findByIsActiveTrue()
            .map(Price::getPrice)
            .orElse(BigDecimal.valueOf(0.0));
    }
}
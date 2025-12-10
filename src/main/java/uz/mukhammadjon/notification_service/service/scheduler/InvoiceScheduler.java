package uz.mukhammadjon.notification_service.service.scheduler;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uz.mukhammadjon.notification_service.service.InvoiceService;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InvoiceScheduler {

    InvoiceService invoiceService;

    @Scheduled(cron = "0 0 0 L * ?")
    public void generateMonthlyInvoices() {
        invoiceService.generateAndSendInvoices();
    }
}
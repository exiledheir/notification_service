package uz.mukhammadjon.notification_service.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.mukhammadjon.notification_service.service.InvoiceService;

@RestController
@RequestMapping("/api/invoice")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InvoiceController {

    InvoiceService invoiceService;
    //TODO: remove and delegeate to other service that sends it.
    @PostMapping("/generate")
    public ResponseEntity<String> generateInvoices() {
        invoiceService.generateAndSendInvoices();
        return ResponseEntity.ok("Invoices generated");
    }
}
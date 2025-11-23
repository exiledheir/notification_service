package uz.mukhammadjon.notification_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.mukhammadjon.notification_service.dto.merchant.MerchantRegistrationRequest;
import uz.mukhammadjon.notification_service.dto.merchant.MerchantRegistrationResponse;
import uz.mukhammadjon.notification_service.dto.notification.NotificationRequest;
import uz.mukhammadjon.notification_service.dto.notification.NotificationResponse;
import uz.mukhammadjon.notification_service.dto.response.NotificationServiceResponse;
import uz.mukhammadjon.notification_service.service.MerchantService;
import uz.mukhammadjon.notification_service.service.NotificationService;

@RestController
@RequestMapping("/api/notification-company-number-1/")
@RequiredArgsConstructor
public class NotificationsController {

    private final MerchantService merchantService;
    private final NotificationService notificationService;

    @PostMapping("/registration")
    public ResponseEntity<NotificationServiceResponse<MerchantRegistrationResponse>> registerMerchant(@Valid @RequestBody MerchantRegistrationRequest request) {
        MerchantRegistrationResponse response = merchantService.registerMerchant(request);

        return ResponseEntity.ok(NotificationServiceResponse.success(response));
    }

    @PostMapping("/notifications/sms")
    public ResponseEntity<NotificationServiceResponse<NotificationResponse>> sendSms(@Valid @RequestBody NotificationRequest request) {
        NotificationResponse response = notificationService.sendSms(request);
        return ResponseEntity.ok(NotificationServiceResponse.success(response));
    }
}

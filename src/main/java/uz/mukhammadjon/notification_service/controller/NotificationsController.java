package uz.mukhammadjon.notification_service.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.mukhammadjon.notification_service.dto.merchant.MerchantRegistrationRequest;
import uz.mukhammadjon.notification_service.dto.merchant.MerchantRegistrationResponse;
import uz.mukhammadjon.notification_service.dto.notification.NotificationEmailRequest;
import uz.mukhammadjon.notification_service.dto.notification.NotificationResponse;
import uz.mukhammadjon.notification_service.dto.notification.NotificationSmsRequest;
import uz.mukhammadjon.notification_service.dto.response.NotificationServiceResponse;
import uz.mukhammadjon.notification_service.service.MerchantService;
import uz.mukhammadjon.notification_service.service.NotificationService;

@RestController
@RequestMapping("/api/notification/")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class NotificationsController {

    MerchantService merchantService;
    NotificationService notificationService;

    @PostMapping("/registration")
    public ResponseEntity<NotificationServiceResponse<MerchantRegistrationResponse>> registerMerchant(@Valid @RequestBody MerchantRegistrationRequest request) {
        MerchantRegistrationResponse response = merchantService.registerMerchant(request);

        return ResponseEntity.ok(NotificationServiceResponse.success(response));
    }

    @PostMapping("/sms")
    public ResponseEntity<NotificationServiceResponse<NotificationResponse>> sendSms(@Valid @RequestBody NotificationSmsRequest request) {
        NotificationResponse response = notificationService.sendSms(request);

        return ResponseEntity.ok(NotificationServiceResponse.success(response));
    }

    @PostMapping("/email")
    public ResponseEntity<NotificationServiceResponse<NotificationResponse>> sendEmail(@Valid @RequestBody NotificationEmailRequest request) {
        NotificationResponse response = notificationService.sendEmail(request);

        return ResponseEntity.ok(NotificationServiceResponse.success(response));
    }
}

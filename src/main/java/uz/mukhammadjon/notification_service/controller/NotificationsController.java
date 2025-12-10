package uz.mukhammadjon.notification_service.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.mukhammadjon.notification_service.constant.enums.Status;
import uz.mukhammadjon.notification_service.dto.merchant.MerchantRegistrationRequest;
import uz.mukhammadjon.notification_service.dto.merchant.MerchantRegistrationResponse;
import uz.mukhammadjon.notification_service.dto.notification.NotificationRequest;
import uz.mukhammadjon.notification_service.dto.notification.NotificationResponse;
import uz.mukhammadjon.notification_service.dto.response.NotificationServiceResponse;
import uz.mukhammadjon.notification_service.service.MerchantService;
import uz.mukhammadjon.notification_service.service.NotificationService;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class NotificationsController {

    MerchantService merchantService;
    NotificationService notificationService;

    @PostMapping("/registration")
    public ResponseEntity<NotificationServiceResponse<MerchantRegistrationResponse>> registerMerchant(
        @Valid @RequestBody MerchantRegistrationRequest request) {
        MerchantRegistrationResponse response = merchantService.registerMerchant(request);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(NotificationServiceResponse.success(response));
    }

    @PostMapping("/sending")
    public ResponseEntity<NotificationServiceResponse<NotificationResponse>> sendNotification(
        @Valid @RequestBody NotificationRequest request) {
        NotificationResponse response = notificationService.sendNotification(request);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(NotificationServiceResponse.success(response));
    }

    //TODO: remove and delegeate to other service that sends it.
    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(
        @PathVariable Long id,
        @RequestParam Status status) {
        notificationService.updateStatus(id, status);
        return ResponseEntity.ok("Status updated: " + status);
    }
}
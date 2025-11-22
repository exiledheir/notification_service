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
import uz.mukhammadjon.notification_service.dto.response.NotificationServiceResponse;
import uz.mukhammadjon.notification_service.service.MerchantService;

@RestController
@RequestMapping("/api/notification-company-number-1/")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @PostMapping("/registration")
    public ResponseEntity<NotificationServiceResponse<MerchantRegistrationResponse>> registerMerchant(@Valid @RequestBody MerchantRegistrationRequest request) {
        MerchantRegistrationResponse response = merchantService.registerMerchant(request);

        return ResponseEntity.ok(NotificationServiceResponse.success(response));
    }
}

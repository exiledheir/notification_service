package uz.mukhammadjon.notification_service.service;

import uz.mukhammadjon.notification_service.dto.merchant.MerchantRegistrationRequest;
import uz.mukhammadjon.notification_service.dto.merchant.MerchantRegistrationResponse;

public interface MerchantService {

    MerchantRegistrationResponse registerMerchant(MerchantRegistrationRequest request);
}

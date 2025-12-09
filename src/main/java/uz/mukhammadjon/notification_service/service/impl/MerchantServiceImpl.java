package uz.mukhammadjon.notification_service.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.mukhammadjon.notification_service.dto.merchant.MerchantRegistrationRequest;
import uz.mukhammadjon.notification_service.dto.merchant.MerchantRegistrationResponse;
import uz.mukhammadjon.notification_service.entity.Merchant;
import uz.mukhammadjon.notification_service.exception.DataExistsException;
import uz.mukhammadjon.notification_service.mapper.MerchantMapper;
import uz.mukhammadjon.notification_service.repository.MerchantRepository;
import uz.mukhammadjon.notification_service.service.MerchantService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MerchantServiceImpl implements MerchantService {

    MerchantRepository repository;
    MerchantMapper mapper;

    PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public MerchantRegistrationResponse registerMerchant(MerchantRegistrationRequest request) {
        if (repository.existsMerchantByTaxNumber(request.getTaxNumber())) {
            throw new DataExistsException("Merchant with tax number already exists");
        }
        String password = generatePassword();


        Merchant merchant = mapper.toEntity(request);
        merchant.setPassword(passwordEncoder.encode(password));

        merchant = repository.save(merchant);

        MerchantRegistrationResponse response = mapper.toResponse(merchant);
        response.setPassword(password);

        return response;
    }

    private String generatePassword() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 12);
    }
}

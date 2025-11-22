package uz.mukhammadjon.notification_service.service.impl;

import lombok.RequiredArgsConstructor;
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
@Transactional(readOnly = true)
public class MerchantServiceImpl implements MerchantService {

    private final MerchantRepository repository;
    private final MerchantMapper mapper;
    private final PasswordEncoder passwordEncoder;

//    id        bigint generated always as identity primary key, auto generated
//    name      varchar(50)  not null, ----
//    webhook   varchar(100) not null, ----
//    tax_number varchar(50)  not null unique, -----
//    created_at timestamp default current_timestamp, auto generated
//    login     varchar(50)  not null unique, -----
//    password  varchar not null auto generate

//    "companyName": "OOO 3 korovi", ---->> name
//    "tax-number": "902731283718937", --->>> tax_number
//    "login": "login", -----> login
//    "webhook": "sss@mail.com" ----->>webhook

    @Transactional
    @Override
    public MerchantRegistrationResponse registerMerchant(MerchantRegistrationRequest request) {
        if (repository.existsMerchantByLoginOrTaxNumber(request.getLogin(), request.getTaxNumber())) {
            throw new DataExistsException("Merchant with login or tax number already exists");
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

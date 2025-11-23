package uz.mukhammadjon.notification_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.mukhammadjon.notification_service.dto.notification.NotificationRequest;
import uz.mukhammadjon.notification_service.dto.notification.NotificationResponse;
import uz.mukhammadjon.notification_service.entity.Merchant;
import uz.mukhammadjon.notification_service.entity.Notification;
import uz.mukhammadjon.notification_service.exception.MerchantNotFoundException;
import uz.mukhammadjon.notification_service.mapper.NotificationMapper;
import uz.mukhammadjon.notification_service.repository.MerchantRepository;
import uz.mukhammadjon.notification_service.repository.NotificationRepository;
import uz.mukhammadjon.notification_service.service.NotificationService;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repository;
    private final NotificationMapper notificationMapper;
    private final MerchantRepository merchantRepository;


//    request-body: {
//    "merchant": 1,  only numbers -->>merchant_id
//    "content": "", size <=250 ----->content
//    "receiver": "" regex 998(90, 91, 93, 97) 3332211 -----> receiver
//}
//
//response-body: {
//    "notificationId": 1
//}

    //    create table notifications
//(
//    id         bigint generated always as identity primary key, ----------
//    status     stats        not null default 'CREATED', default created
//    created_at timestamp not null default current_timestamp, -------
//    updated_at timestamp not null default current_timestamp,-------
//    content    varchar not null,--------
//    merchant_id bigint       not null,---------
//    receiver   varchar(50)  not null,---------
//    constraint fk_merchant foreign key (merchant_id) references merchants (id) on delete cascade
//);
    @Override
    public NotificationResponse sendSms(NotificationRequest request) {
        Merchant merchant = merchantRepository.findById(request.getMerchant()).
            orElseThrow(() -> new MerchantNotFoundException("Merchant with ID: " + request.getMerchant() + ", not found"));

        Notification notification = notificationMapper.toEntity(request);
        notification.setMerchant(merchant);
        notification = repository.save(notification);


        return notificationMapper.toResponse(notification);
    }
}

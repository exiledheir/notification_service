package uz.mukhammadjon.notification_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uz.mukhammadjon.notification_service.dto.merchant.MerchantRegistrationRequest;
import uz.mukhammadjon.notification_service.dto.merchant.MerchantRegistrationResponse;
import uz.mukhammadjon.notification_service.entity.Merchant;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MerchantMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "companyName")
    @Mapping(target = "password", ignore = true)
    Merchant toEntity(MerchantRegistrationRequest request);

    @Mapping(target = "merchantId", source = "merchant.id")
    @Mapping(target = "password", ignore = true)
    MerchantRegistrationResponse toResponse(Merchant merchant);


}

package uz.mukhammadjon.notification_service.dto.merchant;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MerchantRegistrationResponse {

    Long merchantId;
    String password;
}

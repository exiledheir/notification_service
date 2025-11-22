package uz.mukhammadjon.notification_service.dto.merchant;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class MerchantRegistrationResponse {
    private Long merchantId;
    private String password;
}

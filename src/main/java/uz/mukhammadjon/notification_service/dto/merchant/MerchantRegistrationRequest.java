package uz.mukhammadjon.notification_service.dto.merchant;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MerchantRegistrationRequest {

    @NotBlank(message = "company name cannot be blank")
    @Size(max = 50)
    String companyName;
    @NotBlank(message = "tax number cannot be blank")
    @Size(max = 50)
    String taxNumber;
    @NotBlank(message = "login cannot be blank")
    @Size(max = 50)
    String login;
    @NotBlank(message = "webhook cannot be blank")
    @Size(max = 100)
    @Email
    String webhook;
}

package uz.mukhammadjon.notification_service.dto.notification;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEmailRequest {

    @NotBlank(message = "content cant be empty or null")
    @Size(max = 500)
    private String content;
    @NotBlank(message = "reciever cant be blank")
    @Email(message = "enter valid email")
    private String receiver;

}

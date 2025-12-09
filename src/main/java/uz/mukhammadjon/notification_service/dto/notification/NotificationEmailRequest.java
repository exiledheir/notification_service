package uz.mukhammadjon.notification_service.dto.notification;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationEmailRequest {

    @NotBlank(message = "content cant be empty or null")
    @Size(max = 500)
    String content;
    @NotBlank(message = "reciever cant be blank")
    @Email(message = "enter valid email")
    String receiver;
}

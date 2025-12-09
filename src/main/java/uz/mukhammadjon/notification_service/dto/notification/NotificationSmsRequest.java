package uz.mukhammadjon.notification_service.dto.notification;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class NotificationSmsRequest {

    @NotBlank(message = "content cant be empty or null")
    @Size(max = 250)
    String content;
    @NotBlank(message = "reciever cant be blank")
    @Pattern(regexp = "^998(90|91|93|97)\\d{7}$", message = "invalid number")
    String receiver;
}

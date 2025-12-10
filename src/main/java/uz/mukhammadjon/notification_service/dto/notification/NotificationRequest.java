package uz.mukhammadjon.notification_service.dto.notification;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import uz.mukhammadjon.notification_service.constant.enums.Type;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationRequest {

    @NotBlank(message = "content cant be empty or null")
    @Size(max = 250)
    String content;
    @NotNull(message = "reciever cant be blank")
    @Valid
    Receiver receiver;
    @NotNull(message = "type cannot be null")
    Type type;
}

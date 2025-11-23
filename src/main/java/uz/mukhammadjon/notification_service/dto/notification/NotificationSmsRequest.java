package uz.mukhammadjon.notification_service.dto.notification;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class NotificationSmsRequest {
    @NotNull
    @Min(value = 0)
    private Long merchant;
    @NotBlank(message = "content cant be empty or null")
    @Size(max = 250)
    private String content;
    @NotBlank(message = "reciever cant be blank")
    @Pattern(regexp = "^998(90|91|93|97)\\d{7}$", message = "invalid number")
    private String receiver;

}

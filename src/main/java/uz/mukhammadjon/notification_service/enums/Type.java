package uz.mukhammadjon.notification_service.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Type {
    SMS, EMAIL, PUSH;
}

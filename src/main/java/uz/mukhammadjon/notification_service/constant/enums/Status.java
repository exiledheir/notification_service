package uz.mukhammadjon.notification_service.constant.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    CREATED, SENT, FAILED;
}

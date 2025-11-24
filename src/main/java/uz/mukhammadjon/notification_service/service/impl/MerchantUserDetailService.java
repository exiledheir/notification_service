package uz.mukhammadjon.notification_service.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.mukhammadjon.notification_service.entity.Merchant;
import uz.mukhammadjon.notification_service.exception.LoginNotFoundException;
import uz.mukhammadjon.notification_service.repository.MerchantRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantUserDetailService implements UserDetailsService {
    private final MerchantRepository repository;

    @Override
    @NonNull
    public UserDetails loadUserByUsername(@NonNull String login) throws UsernameNotFoundException {
        Merchant merchant = repository.findByLogin(login).
            orElseThrow(() -> new LoginNotFoundException("User with login: " + login + ", not found"));

        return User.builder()
            .username(merchant.getLogin())
            .password(merchant.getPassword())
            .authorities(List.of())
            .build();
    }

}

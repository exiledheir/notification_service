package uz.mukhammadjon.notification_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.mukhammadjon.notification_service.entity.Merchant;

import java.util.Optional;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {

    boolean existsMerchantByTaxNumber(String taxNumber);

    Optional<Merchant> findByLogin(String login);
}

package uz.iaiiai.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.iaiiai.banking.model.entity.Wallet;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByUserId(Long userId);
    Optional<Wallet> findWalletByPayable_Alias(String alias);
}

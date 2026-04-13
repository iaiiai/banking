package uz.iaiiai.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.iaiiai.banking.model.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
    boolean existsByUsername(String username);
}

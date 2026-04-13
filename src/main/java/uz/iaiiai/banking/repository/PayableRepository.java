package uz.iaiiai.banking.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.iaiiai.banking.model.entity.Payable;

import java.util.List;
import java.util.Optional;

public interface PayableRepository extends JpaRepository<Payable, Long> {
    boolean existsByAlias(String alias);
    Optional<Payable> findPayableByAlias(String alias);
    Optional<Payable> findPayableByAliasAndIsActiveTrue(String alias);
    Page<Payable> findPayablesByIsActiveIsTrue(Pageable pageable);
}

package uz.iaiiai.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.iaiiai.banking.model.entity.Transaction;
import uz.iaiiai.banking.model.entity.Wallet;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.timestamp BETWEEN :from AND :to " +
            "AND (t.sender.id = :walletId OR t.recipient.id = :walletId)")
    List<Transaction> findTransactionByTimestampBetween(
            @Param("from")
            LocalDateTime from,
            @Param("to")
            LocalDateTime to,
            @Param("walletId")
            Long walletId
    );
}

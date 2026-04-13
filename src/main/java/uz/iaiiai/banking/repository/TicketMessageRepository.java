package uz.iaiiai.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.iaiiai.banking.model.entity.TicketMessage;

public interface TicketMessageRepository extends JpaRepository<TicketMessage, Long> {
}

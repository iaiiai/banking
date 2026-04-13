package uz.iaiiai.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.iaiiai.banking.model.entity.Ticket;
import uz.iaiiai.banking.model.enumeration.TicketStatus;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findByUserId(Long userId);
    List<Ticket> findByStatus(TicketStatus status);
    List<Ticket> findTicketsByUserId(Long userId);
    Optional<Ticket> findTicketByIdAndUserId(Long ticketId, Long userId);
    Optional<Ticket> findTicketById(Long ticketId);
}

package uz.iaiiai.banking.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "ticket_messages")
@NoArgsConstructor
@Data
public class TicketMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @Column(name = "content", nullable = false)
    private String content;

    @CreationTimestamp
    private LocalDateTime timestamp;

}

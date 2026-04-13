package uz.iaiiai.banking.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uz.iaiiai.banking.model.enumeration.TicketStatus;

import java.util.List;

@Entity
@Table(name = "tickets")
@NoArgsConstructor
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @Column(name = "subject", nullable = false)
    private String subject;

    @OneToMany(mappedBy = "ticket")
    @OrderBy("timestamp ASC")
    private List<TicketMessage> messages;
}

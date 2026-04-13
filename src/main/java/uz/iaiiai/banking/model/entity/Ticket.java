package uz.iaiiai.banking.model.entity;

import jakarta.persistence.*;
import uz.iaiiai.banking.model.enumeration.ChatStatus;

@Entity
@Table(name = "chats")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ChatStatus status;

    
}

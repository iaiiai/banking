package uz.iaiiai.banking.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import uz.iaiiai.banking.model.Auditable;
import uz.iaiiai.banking.model.enumeration.TransactionStatus;
import uz.iaiiai.banking.model.enumeration.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@NoArgsConstructor
@Getter
@Setter
public class Transaction extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sender_wallet_id", nullable = false)
    private Wallet sender;

    @ManyToOne(optional = false)
    @JoinColumn(name = "recipient_wallet_id", nullable = false)
    private Wallet recipient;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @CreationTimestamp
    private LocalDateTime timestamp;
}

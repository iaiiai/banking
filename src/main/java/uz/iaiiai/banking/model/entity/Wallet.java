package uz.iaiiai.banking.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.iaiiai.banking.model.Auditable;
import uz.iaiiai.banking.model.enumeration.WalletType;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "wallets")
@NoArgsConstructor
@Getter
@Setter
public class Wallet extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance = new BigDecimal("0");

    @OneToMany(mappedBy = "sender")
    private List<Transaction> sentTransactions;

    @OneToMany(mappedBy = "recipient")
    private List<Transaction> receivedTransactions;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @OneToOne
    @JoinColumn(name = "payable_id", unique = true)
    private Payable payable;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private WalletType walletType;
}

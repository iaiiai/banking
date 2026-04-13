package uz.iaiiai.banking.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Entity
@Table(name = "payables")
@NoArgsConstructor
@Getter
@Setter
public class Payable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "alias", nullable = false, unique = true)
    private String alias;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "picture_url", nullable = false)
    private String pictureUrl;

    @OneToOne(
            mappedBy = "payable",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            optional = false
    )
    private Wallet wallet;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;
}

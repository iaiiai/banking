package uz.iaiiai.banking.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "services")
@NoArgsConstructor
@Getter
@Setter
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "alias", nullable = false, unique = true)
    private String alias;

    @Column(name = "picture_url", nullable = false)
    private String pictureUrl;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private String price;

    @OneToOne(
            mappedBy = "service",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            optional = false
    )
    private Wallet wallet;
}

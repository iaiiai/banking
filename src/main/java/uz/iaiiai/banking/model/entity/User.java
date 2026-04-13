package uz.iaiiai.banking.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.iaiiai.banking.model.Auditable;

import java.math.BigDecimal;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User extends Auditable {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "username", nullable = false, unique = true)
   private String username;

   @Column(name = "password", nullable = false)
   private String password;

   @OneToOne(
           mappedBy = "user",
           cascade = CascadeType.ALL,
           orphanRemoval = true,
           optional = false
   )
   private Wallet wallet;

   @ManyToOne(optional = false)
   private Role role;

}

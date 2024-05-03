package br.com.rocketdevelopment.picpay.domain.wallet;
import br.com.rocketdevelopment.picpay.domain.users.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity(name = "wallet")
@Table(name = "wallet")
@NoArgsConstructor
public class Wallet{

    public Wallet(BigDecimal balance) {
        this.balance = balance;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal balance;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;


}

package br.com.rocketdevelopment.picpay.domain.transaction;


import br.com.rocketdevelopment.picpay.domain.users.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transactions")
@Table(name = "transactions")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transactions implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "payee_id")
    private User payee;

    @ManyToOne
    @JoinColumn(name = "payer_id")
    private User payer;

    @CreatedDate
    private LocalDateTime transactionTime;
}

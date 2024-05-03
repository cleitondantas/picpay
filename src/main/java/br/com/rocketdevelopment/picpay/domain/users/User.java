package br.com.rocketdevelopment.picpay.domain.users;

import br.com.rocketdevelopment.picpay.domain.wallet.Wallet;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@Entity(name = "users")
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements Serializable {

    public User(Long id) {
        this.id = id;
    }
    public User(String fullName, String document, String email, String password, UserType userType) {
        this.fullName = fullName;
        this.document = document;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }
    public User(String fullName, String document, String email, String password, UserType userType,Wallet wallet) {
        this.fullName = fullName;
        this.document = document;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.wallet = wallet;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Wallet wallet;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
        if (wallet != null) {
            wallet.setUser(this);
        }
    }

}

package br.com.rocketdevelopment.picpay.repository;

import br.com.rocketdevelopment.picpay.domain.users.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long>{

}

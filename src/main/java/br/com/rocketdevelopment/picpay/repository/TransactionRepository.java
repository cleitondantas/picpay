package br.com.rocketdevelopment.picpay.repository;


import br.com.rocketdevelopment.picpay.domain.transaction.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transactions, Long>{

}

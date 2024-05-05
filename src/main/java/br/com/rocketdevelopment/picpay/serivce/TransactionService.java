package br.com.rocketdevelopment.picpay.serivce;


import br.com.rocketdevelopment.picpay.api.dto.TransactionDTO;
import br.com.rocketdevelopment.picpay.domain.users.User;
import br.com.rocketdevelopment.picpay.infrastructure.Authorizer;
import br.com.rocketdevelopment.picpay.infrastructure.NotificationProducer;
import br.com.rocketdevelopment.picpay.repository.TransactionRepository;
import br.com.rocketdevelopment.picpay.domain.transaction.Transactions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final NotificationProducer notificationService;
    private final Authorizer authorizationService;



    public TransactionService(UserService userService , TransactionRepository transactionRepository, NotificationProducer notificationService, Authorizer authorizationService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
        this.notificationService = notificationService;
        this.authorizationService = authorizationService;
    }

    @Transactional
    public TransactionDTO save(TransactionDTO transactionDTO) throws Exception {

        User payer = userService.getUserById(transactionDTO.payer()); //pagador
        userService.isPayerValid(payer, transactionDTO.amount());
        User payee = userService.getUserById(transactionDTO.payee()); //beneficiário
        payer.getWallet().setBalance(payer.getWallet().getBalance().subtract(transactionDTO.amount()));
        payee.getWallet().setBalance(payee.getWallet().getBalance().add(transactionDTO.amount()));

        Transactions transction = Transactions.builder().amount(transactionDTO.amount()).payer(payer).payee(payee).build();
        Transactions save = transactionRepository.save(transction);
        userService.saveOrUpdate(payer);
        userService.saveOrUpdate(payee);

        authorizationService.validateTransaction();
        notificationService.send( "Transaction completed: "+ payer.getEmail() + " to " + payee.getEmail() + " amount: " + transactionDTO.amount());
        return new TransactionDTO(save.getPayee().getId(), save.getPayer().getId(), save.getAmount());
    }



}

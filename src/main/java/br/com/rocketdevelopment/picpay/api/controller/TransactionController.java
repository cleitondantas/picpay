package br.com.rocketdevelopment.picpay.api.controller;

import br.com.rocketdevelopment.picpay.api.dto.TransactionDTO;
import br.com.rocketdevelopment.picpay.exception.TransactionException;
import br.com.rocketdevelopment.picpay.serivce.TransactionService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping("/pay")
    public ResponseEntity<TransactionDTO> pay(@RequestBody TransactionDTO transactionDTO) throws TransactionException {
        TransactionDTO transaction = null;
        try {
            transaction = transactionService.save(transactionDTO);
        } catch (Exception e) {
            log.severe(e.getMessage());
            throw new TransactionException("Error ao realizar transação");
        }
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

}

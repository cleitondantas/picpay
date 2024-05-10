package br.com.rocketdevelopment.picpay.api.controller;


import br.com.rocketdevelopment.picpay.api.dto.TransactionDTO;
import br.com.rocketdevelopment.picpay.exception.TransactionException;
import br.com.rocketdevelopment.picpay.serivce.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    private TransactionDTO transactionDTO;

    @BeforeEach
    public void setup() {
        transactionDTO = new TransactionDTO(1L, 2L,new BigDecimal(100));
    }

    @Test
    public void testPay() throws Exception {
        when(transactionService.save(any(TransactionDTO.class))).thenReturn(transactionDTO);

        ResponseEntity<TransactionDTO> response = transactionController.pay(transactionDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(transactionDTO, response.getBody());

        verify(transactionService, times(1)).save(any(TransactionDTO.class));
    }

    @Test
    public void testPayThrowsException() throws Exception {
        when(transactionService.save(any(TransactionDTO.class))).thenThrow(new TransactionException("Error ao realizar transação"));

        try {
            transactionController.pay(transactionDTO);
        } catch (TransactionException e) {
            assertEquals("Error ao realizar transação", e.getMessage());
        }

        verify(transactionService, times(1)).save(any(TransactionDTO.class));
    }
}
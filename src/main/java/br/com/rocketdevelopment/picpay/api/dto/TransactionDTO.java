package br.com.rocketdevelopment.picpay.api.dto;

import java.math.BigDecimal;

public record TransactionDTO(Long payee, Long payer, BigDecimal amount){
}

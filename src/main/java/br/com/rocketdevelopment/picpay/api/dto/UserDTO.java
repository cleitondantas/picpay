package br.com.rocketdevelopment.picpay.api.dto;

import br.com.rocketdevelopment.picpay.domain.users.UserType;

import java.math.BigDecimal;

public record UserDTO(String fullName,  String document, String email, String password,BigDecimal balance, UserType userType) {
}

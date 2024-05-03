package br.com.rocketdevelopment.picpay.infrastructure;

public interface Authorizer {

    void validateTransaction() throws Exception;
}

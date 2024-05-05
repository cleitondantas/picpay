package br.com.rocketdevelopment.picpay.infrastructure.impl;

import br.com.rocketdevelopment.picpay.configuration.Config;
import br.com.rocketdevelopment.picpay.domain.authorization.Authorization;
import br.com.rocketdevelopment.picpay.exception.ValidadeException;
import br.com.rocketdevelopment.picpay.infrastructure.Authorizer;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class AuthorizationComponent implements Authorizer {


    private final RestClient restClient;

    public AuthorizationComponent(RestClient.Builder builder, Config config) {
        this.restClient  = builder.baseUrl(config.getAutorizationServerUrl()).build();
    }

    public void validateTransaction() throws Exception {
        var isValid = restClient.get().retrieve().toEntity(Authorization.class);

        if (isValid.getStatusCode().is4xxClientError() || !isValid.getBody().isAuthorized()) {
            throw new ValidadeException("Transaction not authorized");
        }
    }
}

package br.com.rocketdevelopment.picpay.infrastructure.impl;

import br.com.rocketdevelopment.picpay.domain.authorization.Authorization;
import br.com.rocketdevelopment.picpay.exception.ValidadeException;
import br.com.rocketdevelopment.picpay.infrastructure.Authorizer;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AuthorizationService implements Authorizer {

    private final RestClient restClient;

    public AuthorizationService(RestClient.Builder builder) {
        this.restClient  = builder.baseUrl("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc").build();
    }

    public void validateTransaction() throws Exception {
        var isValid = restClient.get().retrieve().toEntity(Authorization.class);

        if (isValid.getStatusCode().is4xxClientError() || !isValid.getBody().isAuthorized()) {
            throw new ValidadeException("Transaction not authorized");
        }
    }
}

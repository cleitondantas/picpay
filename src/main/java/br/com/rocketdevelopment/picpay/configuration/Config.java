package br.com.rocketdevelopment.picpay.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class Config {

    @Value("${picpay.autorization.server.url}")
    private String autorizationServerUrl;

    @Value("${picpay.notification.server.url}")
    private String notificationServerUrl;

}

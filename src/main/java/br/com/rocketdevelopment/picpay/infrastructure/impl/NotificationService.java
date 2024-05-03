package br.com.rocketdevelopment.picpay.infrastructure.impl;

import br.com.rocketdevelopment.picpay.infrastructure.Notification;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements Notification {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void send(String message) {
        amqpTemplate.convertAndSend("pagamento-request-exchange", "pagamento-request-rout-key", message);
    }
}

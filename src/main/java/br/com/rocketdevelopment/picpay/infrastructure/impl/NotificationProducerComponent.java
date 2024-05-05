package br.com.rocketdevelopment.picpay.infrastructure.impl;

import br.com.rocketdevelopment.picpay.infrastructure.NotificationProducer;
import lombok.extern.java.Log;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log
@Component
public class NotificationProducerComponent implements NotificationProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void send(String message) {
        amqpTemplate.convertAndSend("myexchange", "pagamento-request-rout-key", message);
        log.info("Notificação enviada: " + message);
    }
}

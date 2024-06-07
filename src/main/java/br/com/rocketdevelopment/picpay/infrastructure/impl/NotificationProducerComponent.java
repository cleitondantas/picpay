package br.com.rocketdevelopment.picpay.infrastructure.impl;

import br.com.rocketdevelopment.picpay.infrastructure.NotificationProducer;
import lombok.extern.java.Log;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Log
@Component
public class NotificationProducerComponent implements NotificationProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public NotificationProducerComponent(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(String message) {
        kafkaTemplate.send("transaction-notification", message);
        log.info("Notificação enviada: " + message);
    }
}

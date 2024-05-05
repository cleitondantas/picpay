package br.com.rocketdevelopment.picpay.infrastructure;
import org.springframework.messaging.Message;

public interface NotificationConsumer {

    void consume(Message message) throws Exception;
}

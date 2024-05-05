package br.com.rocketdevelopment.picpay.infrastructure;

public interface NotificationProducer {

    void send(String message);
}

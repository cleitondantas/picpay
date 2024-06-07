package br.com.rocketdevelopment.picpay.serivce;


import br.com.rocketdevelopment.picpay.configuration.Config;
import br.com.rocketdevelopment.picpay.domain.notification.Notification;
import br.com.rocketdevelopment.picpay.exception.NotificationException;
import br.com.rocketdevelopment.picpay.infrastructure.NotificationConsumer;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.Objects;

@Service
@Log
public class NotificationConsumerService implements NotificationConsumer {

    private final RestClient restClient;

    public NotificationConsumerService(Config config, RestClient.Builder builder) {
        this.restClient = builder.baseUrl(config.getNotificationServerUrl()).build();
    }

    @KafkaListener(topics = "transaction-notification", groupId = "picpay-desafio-backend")
    @Override
    public void consume(Message message) throws NotificationException {
        try {
            ResponseEntity<Notification> entity = restClient.get()
                    .header("Authorization", message.getPayload().toString())
                    .retrieve()
                    .toEntity(Notification.class);
            if (entity.getStatusCode().isError() && !Objects.requireNonNull(entity.getBody()).isNotified()) {
                log.severe("Erro Ao realizar notificação");
                throw new NotificationException("Error on notify");
            }
        } catch (Exception e) {
            log.severe("Erro ao realizar notificação");
            throw new NotificationException("Error on notify");
        }
        log.info("Notificação realizada com sucesso"+ message.getPayload());
    }
}

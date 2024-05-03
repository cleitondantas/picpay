package br.com.rocketdevelopment.picpay;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

@SpringBootApplication
@EnableJdbcAuditing
@EnableRabbit
public class PicpayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PicpayApplication.class, args);
    }

}

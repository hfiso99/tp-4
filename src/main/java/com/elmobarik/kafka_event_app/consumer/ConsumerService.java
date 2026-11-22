package com.elmobarik.kafka_event_app.consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class ConsumerService {

    @Bean
    public Consumer<String> messageConsumer() {

        return message -> {
            System.out.println("================================");
            System.out.println("NOUVEAU MESSAGE REÇU DE KAFKA");
            System.out.println("Message : " + message);
            System.out.println("================================");
        };
    }
}

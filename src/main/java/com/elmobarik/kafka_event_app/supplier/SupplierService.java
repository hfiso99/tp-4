package com.elmobarik.kafka_event_app.supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.function.Supplier;

@Configuration
public class SupplierService {

    @Bean
    public Supplier<String> eventSupplier() {

        return () -> {
            String event = "Evenement automatique genere le : "
                    + LocalDateTime.now();

            System.out.println("SUPPLIER : " + event);

            return event;
        };
    }
}

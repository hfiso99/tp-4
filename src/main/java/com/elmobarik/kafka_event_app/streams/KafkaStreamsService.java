package com.elmobarik.kafka_event_app.streams;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@Configuration
@EnableKafkaStreams
public class KafkaStreamsService {

    @Bean
    public KStream<String, String> processMessages(StreamsBuilder builder) {

        KStream<String, String> messages =
                builder.stream("messages-topic");

        messages
                .groupBy((key, value) -> "total-messages")
                .count()
                .toStream()
                .to(
                        "analytics-topic",
                        Produced.with(Serdes.String(), Serdes.Long())
                );

        System.out.println("Kafka Streams : traitement des messages activé");

        return messages;
    }
}

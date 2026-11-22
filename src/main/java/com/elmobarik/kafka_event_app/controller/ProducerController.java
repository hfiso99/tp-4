package com.elmobarik.kafka_event_app.controller;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class ProducerController {

    private final StreamBridge streamBridge;

    public ProducerController(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @PostMapping
    public ResponseEntity<String> sendMessage(
            @RequestBody String message) {

        boolean sent = streamBridge.send(
                "producer-out-0",
                message
        );

        if (sent) {
            return ResponseEntity.ok(
                    "Message envoyé à Kafka : " + message
            );
        }

        return ResponseEntity.internalServerError()
                .body("Erreur lors de l'envoi du message");
    }
}

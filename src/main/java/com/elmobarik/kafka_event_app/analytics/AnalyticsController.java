package com.elmobarik.kafka_event_app.analytics;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AtomicLong totalMessages = new AtomicLong(0);

    @KafkaListener(
            topics = "analytics-topic",
            groupId = "analytics-display-group"
    )
    public void receiveAnalytics(Long count) {

        totalMessages.set(count);

        System.out.println("================================");
        System.out.println("ANALYSE KAFKA STREAMS");
        System.out.println("Nombre total de messages : " + count);
        System.out.println("================================");
    }

    @GetMapping
    public long getAnalytics() {
        return totalMessages.get();
    }
}

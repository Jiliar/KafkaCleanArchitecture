package com.jsolution.kafkacleanarchitecture.domain.model.gateways;

import com.jsolution.kafkacleanarchitecture.domain.model.KafkaMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;

@Repository
public interface KafkaMessageRepository {
    String send(KafkaMessage kafkaMessage, KafkaTemplate<String, String> kafkaTemplate);
}

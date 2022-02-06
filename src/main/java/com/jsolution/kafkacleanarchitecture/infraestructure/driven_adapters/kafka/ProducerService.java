package com.jsolution.kafkacleanarchitecture.infraestructure.driven_adapters.kafka;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class ProducerService {

    private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);
    
    private KafkaTemplate<String, String> kafkaTemplate;

    public ProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public ProducerRecord sendMessage(String message, String kafkaTopic){
        ProducerRecord answer = null;
        try {
            logger.info(String.format("#### -> Producing message -> %s", message));
            var result = this.kafkaTemplate.send(kafkaTopic, message);
            answer = result.get().getProducerRecord();
        }catch (InterruptedException | ExecutionException e){
            logger.error(String.format("#### -> Exception -> %s", e.getLocalizedMessage()));
        }
        return answer;
    }
}
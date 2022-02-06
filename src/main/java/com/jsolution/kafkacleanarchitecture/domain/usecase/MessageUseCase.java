package com.jsolution.kafkacleanarchitecture.domain.usecase;

import com.jsolution.kafkacleanarchitecture.domain.model.KafkaMessage;
import com.jsolution.kafkacleanarchitecture.domain.model.gateways.KafkaMessageRepository;
import com.jsolution.kafkacleanarchitecture.infraestructure.driven_adapters.service.MessageImplService;
import com.jsolution.kafkacleanarchitecture.infraestructure.entrypoints.http.InputConsumerKafkaRequest;
import com.jsolution.kafkacleanarchitecture.infraestructure.entrypoints.http.InputProducerKafkaRequest;
import org.springframework.kafka.core.KafkaTemplate;

public class MessageUseCase {


    private final KafkaMessageRepository kafkaMessageRepository;

    public MessageUseCase(MessageImplService messageImplService) {
        this.kafkaMessageRepository = messageImplService;
    }

    public String send(InputProducerKafkaRequest message, KafkaTemplate<String, String>  kafkaTemplate){
        KafkaMessage msg = new KafkaMessage(message.getMessage(), message.getTopic());
        return kafkaMessageRepository.send(msg, kafkaTemplate);
    }

}

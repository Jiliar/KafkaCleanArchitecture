package com.jsolution.kafkacleanarchitecture.infraestructure.driven_adapters.service;

import com.jsolution.kafkacleanarchitecture.domain.model.KafkaMessage;
import com.jsolution.kafkacleanarchitecture.domain.model.gateways.KafkaMessageRepository;
import com.jsolution.kafkacleanarchitecture.infraestructure.driven_adapters.kafka.ProducerService;
import org.springframework.kafka.core.KafkaTemplate;

public class MessageImplService implements KafkaMessageRepository {

    @Override
    public String send(KafkaMessage kafkaMessage, KafkaTemplate<String, String> kafkaTemplate) {
        String answer;
        ProducerService producer = new ProducerService(kafkaTemplate);
        var output = producer.sendMessage(kafkaMessage.getMessage(), kafkaMessage.getTopic());
        if(output != null){
            answer = "¡Message has been sent successfully!";
        }else{
            answer = "¡Ops, A problem has occurred!";
        }
        return answer;
    }

}

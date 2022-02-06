package com.jsolution.kafkacleanarchitecture.infraestructure.entrypoints;
import com.jsolution.kafkacleanarchitecture.domain.usecase.MessageUseCase;
import com.jsolution.kafkacleanarchitecture.infraestructure.driven_adapters.service.MessageImplService;
import com.jsolution.kafkacleanarchitecture.infraestructure.entrypoints.http.InputConsumerKafkaRequest;
import com.jsolution.kafkacleanarchitecture.infraestructure.entrypoints.http.InputProducerKafkaRequest;
import com.jsolution.kafkacleanarchitecture.infraestructure.entrypoints.http.OutputResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("MessageController")
public class MessageController {

    private MessageUseCase messageUseCase;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @PostMapping(value = "send", produces = "application/hal+json;charset=utf8")
    public ResponseEntity sendMessage(@Valid @RequestBody InputProducerKafkaRequest message) {
        ResponseEntity result;
        OutputResponse answer;
        messageUseCase = new MessageUseCase(new MessageImplService());
        logger.info(message.toJson());
        try {
            String aux = messageUseCase.send(message, kafkaTemplate);
            answer = new OutputResponse(aux);
            result = new ResponseEntity(answer.toJson(), HttpStatus.OK);
            logger.info(answer.toJson());
        } catch (Exception e) {
            answer = new OutputResponse(e.getLocalizedMessage());
            result = new ResponseEntity(answer.toJson(), HttpStatus.INTERNAL_SERVER_ERROR);
            logger.error(answer.toJson());
        }
        return result;
    }

}

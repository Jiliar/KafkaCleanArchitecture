package com.jsolution.kafkacleanarchitecture.infraestructure.entrypoints.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputProducerKafkaRequest {

    @NotNull
    @JsonProperty("message")
    private String message;

    @NotNull
    @JsonProperty("topic")
    private String topic;

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}

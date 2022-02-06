package com.jsolution.kafkacleanarchitecture.infraestructure.entrypoints.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutputResponse {

    @NotNull
    @JsonProperty("output")
    private String output;

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}

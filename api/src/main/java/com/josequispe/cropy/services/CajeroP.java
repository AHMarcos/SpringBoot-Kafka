package com.josequispe.cropy.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.josequispe.cropy.model.BilleteraEnviar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CajeroP {
    @Autowired
    private KafkaTemplate<String, String>  kafkaTemplate;

    private final String kafkaTopic = "comunicacion";

    @Autowired
    ObjectMapper objectMapper;

    public  void send(BilleteraEnviar billeteraEnviar) throws JsonProcessingException {
        System.out.println(billeteraEnviar);
        kafkaTemplate.send(kafkaTopic, objectMapper.writeValueAsString(billeteraEnviar));
    }
}

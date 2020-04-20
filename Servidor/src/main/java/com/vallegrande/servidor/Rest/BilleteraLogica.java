package com.vallegrande.servidor.Rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vallegrande.servidor.Modelo.Billetera;
import com.vallegrande.servidor.Modelo.BilleteraRecibir;
import com.vallegrande.servidor.Repository.BilleteraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RestController
@Service

public class BilleteraLogica {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    BilleteraRepository billeteraRepository;

    @KafkaListener(topics = "comunicacion", groupId = "0")
    public void actulizarBilletra(String messages) throws IOException {
        BilleteraRecibir billeteraRecibir = objectMapper.readValue(messages, BilleteraRecibir.class);
       Optional<Billetera> billetera = billeteraRepository.findByNumtarjeta(billeteraRecibir.getNumtarjeta());
        if (billetera.isPresent()){
            billetera.get().setSaldoinic(billetera.get().getSaldoinic() + billeteraRecibir.getSaldoinic());
            billeteraRepository.save(billetera.get());
            System.out.println("transaccion finalizada correctamente");
        }else{
            System.out.println("no se hizo la transaccion");
        }
    }

}

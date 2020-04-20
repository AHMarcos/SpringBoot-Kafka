package com.josequispe.cropy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.josequispe.cropy.model.BilleteraEnviar;
import com.josequispe.cropy.model.Cajero;
import com.josequispe.cropy.repository.CajeroRepository;
import com.josequispe.cropy.services.CajeroP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Service
@RequestMapping("/api")
public class CajeroLogica {

    @Autowired
    CajeroRepository repo;
    @Autowired
    CajeroP cajeroP;

    @Autowired
    CajeroP cajeroKafka;

    @PostMapping("/pedir-saldo")
    public Cajero solicitarCredito(@RequestParam("idCajero") String idCajero,
                                   @RequestParam("tarjeta") String tarjeta,
                                   @RequestParam("saldoPedir") Double saldoPedir) throws JsonProcessingException {

        Optional<Cajero> cajero1 = repo.findById(idCajero);

        if (cajero1.isPresent() && (cajero1.get().getSaldo() >= saldoPedir && saldoPedir > 0)) {
            cajero1.get().setSaldo(cajero1.get().getSaldo() - saldoPedir);
            repo.save(cajero1.get());
            BilleteraEnviar billeteraEnviar = new BilleteraEnviar();
            billeteraEnviar.setNumtarjeta(tarjeta);
            billeteraEnviar.setSaldoinic(saldoPedir);
            cajeroKafka.send(billeteraEnviar);
            System.out.println("Transacción Completada satisfactoriamente :D");
            return cajero1.get();
        } else {
            System.out.println("El numero de tarjeta es invalido o cantidad insuficiente");
        }
        return  null;
    }
}

package com.vallegrande.servidor.Repository;

import com.vallegrande.servidor.Modelo.Billetera;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BilleteraRepository extends MongoRepository<Billetera, String> {

    Optional<Billetera> findByNumtarjeta(String numtarjeta);

}

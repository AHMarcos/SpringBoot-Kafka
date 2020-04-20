package com.josequispe.cropy.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "cajero")
@Data
public class Cajero {

    @Id
    private String _id;
    private String ruc;
    private Double saldo;
    private String tarjeta;

}
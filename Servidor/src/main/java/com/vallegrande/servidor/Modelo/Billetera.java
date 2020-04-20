package com.vallegrande.servidor.Modelo;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "billetera")
@Data
public class Billetera {
    @Id
    private String id;
    private String nombre;
    private String numtarjeta;
    private Double saldoinic;

}

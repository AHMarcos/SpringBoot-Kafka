package com.josequispe.cropy.controller;

import java.util.List;
import java.util.Optional;

import com.josequispe.cropy.model.Cajero;
import com.josequispe.cropy.repository.CajeroRepository;

import com.josequispe.cropy.services.CajeroP;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cajero")
public class CajeroController {

    @Autowired
    CajeroRepository repo;
    @Autowired
    CajeroP cajeroP;

    @GetMapping("/listarcajero")
    public List<Cajero> listar(){
        return repo.findAll();
    }

    @GetMapping("/test/{_id}")
    public void listar(@PathVariable String _id){
        repo.findById(_id);
    }
    // GET POST PUT DELETE

    @PostMapping("/create")
    public Cajero guardar(@RequestBody Cajero cajero){
        return repo.save(cajero);
    }

    @PutMapping("/update/{_id}")
    public Cajero updatecajero(@PathVariable String _id, @RequestBody Cajero cajero){
        return  repo.save(cajero);
    }

    @DeleteMapping("/update/{_id}")
    public String eliminarcajero(@PathVariable String _id){
        Optional<Cajero> cajero = repo.findById(_id);
        repo.delete(cajero.get());
        return "cajero eliminado"+_id;
    }
}
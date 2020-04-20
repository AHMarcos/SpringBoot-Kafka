package com.vallegrande.servidor.Rest;

import com.vallegrande.servidor.Modelo.Billetera;
import com.vallegrande.servidor.Repository.BilleteraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BilleteraController {

    @Autowired
    private BilleteraRepository personaService;

    @GetMapping("/listarbilletera")
    public List<Billetera> read(){
        return personaService.findAll();
    }

    //CREATE
    @PostMapping("/create")
    public Billetera save(@RequestBody Billetera modelo){
        return personaService.save(modelo);
    }

    @PutMapping("/update/{id}")
    public Billetera update(@RequestBody Billetera modelo, @PathVariable String id){
        return personaService.save(modelo);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        Optional<Billetera> persona  = personaService.findById(id);
        personaService.delete(persona.get());
            return "persona eliminada " +id;
    }
}

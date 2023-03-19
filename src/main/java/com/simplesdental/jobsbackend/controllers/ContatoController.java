package com.simplesdental.jobsbackend.controllers;

import com.simplesdental.jobsbackend.dto.ContatoDTO;
import com.simplesdental.jobsbackend.models.Contato;
import com.simplesdental.jobsbackend.services.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contato")
public class ContatoController {

    @Autowired
    ContatoService contatoService;

    @GetMapping
    public ResponseEntity<List<Contato>> findContatoByParam(
            @RequestParam(value = "param", defaultValue = "") String param,
            @RequestParam(value = "fields", defaultValue = "") String fields
    ){
        return this.contatoService.getContatoByParam(param, fields);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> findContato(@PathVariable("id") Long id){
        return this.contatoService.getContatoById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Contato> createContato(@RequestBody ContatoDTO dto ){

        return this.contatoService.createContato(dto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Contato> updateContato(@PathVariable("id") Long id, @RequestBody ContatoDTO dto) {

        return this.contatoService.updateContato(id, dto);
    }

    @DeleteMapping("/{id}")
    public String DeletarUsuario(@PathVariable("id") Long id){

        return this.contatoService.deleteContato(id);
    }
}

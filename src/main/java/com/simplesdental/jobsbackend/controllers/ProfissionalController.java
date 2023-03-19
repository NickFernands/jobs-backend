package com.simplesdental.jobsbackend.controllers;

import com.simplesdental.jobsbackend.dto.ProfissionalDTO;
import com.simplesdental.jobsbackend.models.Profissional;
import com.simplesdental.jobsbackend.services.ProfissionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/profissional")
public class ProfissionalController {

    @Autowired
    ProfissionalService profissionalService;

    @GetMapping
    public ResponseEntity<List<Profissional>> findProfissionalByParam(
            @RequestParam(value = "param", defaultValue = "") String param,
            @RequestParam(value = "fields", defaultValue = "") String fields
    ){
        return this.profissionalService.getProfissionalByParam(param, fields);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profissional> findProfissional(@PathVariable("id") Long id){
        return this.profissionalService.getProfissionalById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Profissional createProfissional(@RequestBody ProfissionalDTO dto ){

        return this.profissionalService.createProfissional(dto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Profissional> updateProfissional(@PathVariable("id") Long id, @RequestBody ProfissionalDTO dto) {

        return this.profissionalService.updateProfissional(id, dto);
    }

    @DeleteMapping("/{id}")
    public String DeletarUsuario(@PathVariable("id") Long id){

        return this.profissionalService.deleteProfissional(id);
    }
}

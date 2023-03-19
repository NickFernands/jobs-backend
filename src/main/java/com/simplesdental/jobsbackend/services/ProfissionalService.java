package com.simplesdental.jobsbackend.services;

import com.simplesdental.jobsbackend.dto.ContatoDTO;
import com.simplesdental.jobsbackend.dto.ProfissionalDTO;
import com.simplesdental.jobsbackend.models.Contato;
import com.simplesdental.jobsbackend.models.Profissional;
import com.simplesdental.jobsbackend.repositories.ContatoRepository;
import com.simplesdental.jobsbackend.repositories.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProfissionalService {

    @Autowired
    ProfissionalRepository profissionalRepository;

    @Autowired
    ContatoRepository contatoRepository;

    public ResponseEntity<List<Profissional>> getProfissionalByParam(String param, String fields) {

        try {

            List<Profissional> profissionals = this.profissionalRepository.findByParameterAndFields(param, fields);

            if(this.profissionalRepository.findByParameterAndFields(param, fields).size() == 0) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(profissionals);

         } catch (Error error) {

        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Profissional> getProfissionalById(Long id) {

        try {

            Optional<Profissional> profissionalOptional = profissionalRepository.findById(id);
            if (profissionalOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Profissional profissional = profissionalOptional.get();
            return ResponseEntity.ok(profissional);

        } catch (UnsupportedOperationException error) {

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Profissional createProfissional(ProfissionalDTO dto) {

        try {

            dto.parseDate();

            Profissional profissional = new Profissional();
            profissional.setNome(dto.getNome());
            profissional.setCargo(dto.getCargo());
            profissional.setNascimento(dto.getNascimento());
            profissional.setDataCriacao(LocalDate.now());

            Profissional savedProfissional = profissionalRepository.save(profissional);

            dto.getContatos().forEach(contatoDTO -> {
                Contato buildedContato = contatoBuilder(profissional, contatoDTO);
                contatoRepository.save(buildedContato);
                savedProfissional.addContato(buildedContato);
            });

            return profissionalRepository.save(savedProfissional);

        } catch (UnsupportedOperationException e) {

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    private Contato contatoBuilder(Profissional profissional, ContatoDTO dto) {

        Contato Contato = new Contato();
        Contato.setNome(dto.getNome());
        Contato.setContato(dto.getContato());
        Contato.setProfissional(profissional);

        return Contato;
    }

    public ResponseEntity<Profissional> updateProfissional(Long id, ProfissionalDTO dto) {

        try {
            Optional<Profissional> profissionalSearched = profissionalRepository.findById(id);
            if (profissionalSearched.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Profissional profissional = profissionalSearched.get();


            profissional.setIdProfssional(id);
            if(!dto.getNome().isEmpty()) { profissional.setNome(dto.getNome());}
            if(dto.getCargo() != null) { profissional.setCargo(dto.getCargo()); }
            if(dto.getNascimento() != null) { profissional.setNascimento(dto.getNascimento()); }

            for(ContatoDTO contatos : dto.getContatos()) {
                Contato contato = new Contato();

                contato.setIdContato(contatos.getIdContato());
                contato.setNome(contatos.getNome());
                contato.setContato(contatos.getContato());

                Optional<Profissional> contatoProfisional = profissionalSearched;
                contatoProfisional.ifPresent(contato::setProfissional);
                contatoRepository.save(contato);
                profissional.addContato(contato);
            }

            return ResponseEntity.ok(profissionalRepository.save(profissional));

        } catch (UnsupportedOperationException e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    public String deleteProfissional(Long id) {

        if (!profissionalRepository.existsById(id)) {
            return ResponseEntity.notFound().build().toString();
        }

        profissionalRepository.deleteById(id);

        return ResponseEntity.noContent().build().toString();
    }
}

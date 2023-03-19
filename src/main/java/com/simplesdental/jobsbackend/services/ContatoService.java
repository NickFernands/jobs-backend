package com.simplesdental.jobsbackend.services;

import com.simplesdental.jobsbackend.dto.ContatoDTO;
import com.simplesdental.jobsbackend.models.Contato;
import com.simplesdental.jobsbackend.models.Profissional;
import com.simplesdental.jobsbackend.repositories.ContatoRepository;
import com.simplesdental.jobsbackend.repositories.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    ContatoRepository contatoRepository;

    @Autowired
    ProfissionalRepository profissionalRepository;

    public ResponseEntity<List<Contato>> getContatoByParam(String param, String fields) {

        try {

            List<Contato> contacts = this.contatoRepository.findByParameterAndFields(param, fields);

            if(contacts.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(contacts);

        } catch (Error error) {

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Contato> getContatoById(Long id) {
        try {

            Optional<Contato> contatoOptional = contatoRepository.findById(id);
            if (contatoOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Contato contato = contatoOptional.get();
            return ResponseEntity.ok(contato);

        } catch (Error error) {

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Contato> createContato(ContatoDTO dto) {

        try {

            Optional<Profissional> findProfessional = profissionalRepository.findById(dto.getProfissionalId());
            if (findProfessional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Contato contato = new Contato();
            contato.setNome(dto.getNome());
            contato.setContato(dto.getContato());
            contato.setProfissional(findProfessional.get());

            contatoRepository.save(contato);

            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (UnsupportedOperationException e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    public ResponseEntity<Contato> updateContato(Long id, ContatoDTO dto) {

        try {

            Optional<Contato> contatoSearched = contatoRepository.findById(id);
            if (contatoSearched.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Contato contato = contatoSearched.get();
            contato.setIdContato(id);
            contato.setNome(dto.getNome());
            contato.setContato(dto.getContato());

            Optional<Profissional> contatoProfessional = Optional.empty();

            if(!dto.getProfissionalId().equals(contato.getProfissional().getIdProfssional())) {
                contatoProfessional = profissionalRepository.findById(dto.getProfissionalId());

                if (contatoProfessional.isEmpty()) {
                    return ResponseEntity.notFound().build();
                }
            }

            contatoProfessional.ifPresent(contato::setProfissional);


            return ResponseEntity.ok(contatoRepository.save(contato));

        } catch (UnsupportedOperationException e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    public String deleteContato(Long id) {

        if (!contatoRepository.existsById(id)) {
            return ResponseEntity.notFound().build().toString();
        }

        contatoRepository.deleteById(id);

        return ResponseEntity.noContent().build().toString();
    }
}

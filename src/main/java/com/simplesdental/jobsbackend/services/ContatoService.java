package com.simplesdental.jobsbackend.services;

import com.simplesdental.jobsbackend.dto.ContatoDTO;
import com.simplesdental.jobsbackend.models.Contato;
import com.simplesdental.jobsbackend.models.Profissional;
import com.simplesdental.jobsbackend.repositories.ContatoRepository;
import com.simplesdental.jobsbackend.repositories.ProfissionalRepository;
import com.simplesdental.jobsbackend.responses.FindContatoByParameterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    ContatoRepository contatoRepository;

    @Autowired
    ProfissionalRepository profissionalRepository;

    public List<FindContatoByParameterResponse> getContatoByParam(String param, List<String> fields) {

        try {

            List<Contato> contatos = this.contatoRepository.findByParameterAndFields(param);
            List<FindContatoByParameterResponse> contatoResponse = new ArrayList<FindContatoByParameterResponse>();

            for(Contato contato : contatos) {
                FindContatoByParameterResponse contatoElement = new FindContatoByParameterResponse();
                contatoElement.setIdContato(contato.getIdContato());
                contatoElement.setNome(contato.getNome());
                contatoElement.setContato(contato.getContato());
                contatoElement.setProfissional(contato.getProfissional());

                contatoResponse.add(contatoElement);
            }


            this.contatoFilter(contatoResponse, fields);

            return contatoResponse;

        } catch (Error error) {

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void contatoFilter(List<FindContatoByParameterResponse> contatoResponse, List<String> fields) {
        for(FindContatoByParameterResponse contato: contatoResponse) {
            if (!fields.contains("idContato")) {
                contato.setIdContato(null);
            }
            if (!fields.contains("nome")) {
                contato.setNome(null);
            }
            if (!fields.contains("contato")) {
                contato.setContato(null);
            }
            if (!fields.contains("profissional")) {
                contato.setProfissional(null);
            }
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

    public ResponseEntity<?> deleteContato(Long id) {

        if (!contatoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        contatoRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}

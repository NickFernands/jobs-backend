package com.simplesdental.jobsbackend.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.simplesdental.jobsbackend.models.Profissional;

public class FindContatoByParameterResponse {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long idContato;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String nome;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String contato;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Profissional profissional;

    public Long getIdContato() {
        return idContato;
    }

    public void setIdContato(Long idContato) {
        this.idContato = idContato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }
}

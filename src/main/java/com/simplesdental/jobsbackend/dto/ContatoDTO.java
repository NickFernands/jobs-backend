package com.simplesdental.jobsbackend.dto;


public class ContatoDTO {

    private long idContato;
    private String nome;

    private String contato;

    private Long profissionalId;

    public ContatoDTO(String nome, String contato, Long profissionalId) {
        this.nome = nome;
        this.contato = contato;
        this.profissionalId = profissionalId;
    }

    public ContatoDTO(String nome, String contato) {
        this.nome = nome;
        this.contato = contato;
    }

    public ContatoDTO() {
    }

    public long getIdContato() {
        return idContato;
    }

    public void setIdContato(long idContato) {
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

    public Long getProfissionalId() {
        return profissionalId;
    }

    public void setProfissionalId(Long profissionalId) {
        this.profissionalId = profissionalId;
    }

}

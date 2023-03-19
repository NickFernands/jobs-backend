package com.simplesdental.jobsbackend.models;


import jakarta.persistence.*;


@Entity
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContato;

    private String nome;

    private String contato;

    @ManyToOne(optional = false)
    @JoinColumn(name="idProfissional")
    private Profissional profissional;

    public Contato(Long idContato, String nome, String contato, Profissional profissional) {
        this.idContato = idContato;
        this.nome = nome;
        this.contato = contato;
        this.profissional = profissional;
    }

    public Contato(String nome, String contato, Profissional profissional) {
        this.nome = nome;
        this.contato = contato;
        this.profissional = profissional;
    }

    public Contato() {
    }

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

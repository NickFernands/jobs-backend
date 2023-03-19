package com.simplesdental.jobsbackend.models;

import com.simplesdental.jobsbackend.utils.Cargo;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProfssional;

    private String nome;

    private Cargo cargo;

    private LocalDate nascimento;

    @Column(name = "data_de_criação")
    private LocalDate dataCriacao;

    @OneToMany(mappedBy = "profissional", fetch = FetchType.EAGER)
    private List<Contato> contatos = new ArrayList<>();

    public Profissional(Long idProfssional, String nome, Cargo cargo, LocalDate nascimento, LocalDate dataCriacao, List<Contato> contatos) {
        this.idProfssional = idProfssional;
        this.nome = nome;
        this.cargo = cargo;
        this.nascimento = nascimento;
        this.dataCriacao = dataCriacao;
        this.contatos = contatos;
    }

    public Profissional(String nome, Cargo cargo, LocalDate nascimento, List<Contato> contatos) {
        this.nome = nome;
        this.cargo = cargo;
        this.nascimento = nascimento;
        this.contatos = contatos;
    }

    public Profissional() {
    }

    public Long getIdProfssional() {
        return idProfssional;
    }

    public void setIdProfssional(Long idProfssional) {
        this.idProfssional = idProfssional;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public void addContato(Contato contato) {
        this.contatos.add(contato);
    }
}

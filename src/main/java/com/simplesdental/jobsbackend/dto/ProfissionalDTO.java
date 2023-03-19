package com.simplesdental.jobsbackend.dto;


import com.simplesdental.jobsbackend.utils.Cargo;

import java.time.LocalDate;
import java.util.List;

public class ProfissionalDTO {

    private String nome;

    private Cargo cargo;

    private LocalDate nascimento;

    private String nascimentoString;

    private List<ContatoDTO> contatos;

    public ProfissionalDTO(String nome, Cargo cargo, String nascimentoString, List<ContatoDTO> contatos) {
        this.nome = nome;
        this.cargo = cargo;
        this.nascimentoString = nascimentoString;
        this.contatos = contatos;
    }

    public ProfissionalDTO() {
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

    public List<ContatoDTO> getContatos() {
        return contatos;
    }

    public void setContatos(List<ContatoDTO> contatos) {
        this.contatos = contatos;
    }

    public String getNascimentoString() {
        return nascimentoString;
    }

    public void setNascimentoString(String nascimentoString) {
        this.nascimentoString = nascimentoString;
    }

    public void parseDate() {
        this.nascimento = LocalDate.parse(getNascimentoString());
    }

}

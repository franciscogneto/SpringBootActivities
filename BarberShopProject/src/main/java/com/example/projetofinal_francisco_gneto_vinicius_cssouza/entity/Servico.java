package com.example.projetofinal_francisco_gneto_vinicius_cssouza.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Servico implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable=false)
    private String nome;

    @Column(nullable=false)
    private double preco;

    @Column(nullable=false)
    private int duracao;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_BARBEARIA")
    Barbearia barbearia;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "servico_agendamento",
        uniqueConstraints = @UniqueConstraint(columnNames = { "agendamento_id", "servico_id" }),
        joinColumns = @JoinColumn(name = "servico_id"),
        inverseJoinColumns = @JoinColumn(name = "agendamento_id"))
    private List<Agendamento> agendamentos;

    public Servico() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    public Barbearia getBarbearia() {
        return barbearia;
    }

    public void setBarbearia(Barbearia barbearia) {
        this.barbearia = barbearia;
    }

    @Override
    public String toString() {
        return "Servico [duracao=" + duracao + ", id=" + id + ", nome=" + nome + ", preco=" + preco + "]";
    }
}
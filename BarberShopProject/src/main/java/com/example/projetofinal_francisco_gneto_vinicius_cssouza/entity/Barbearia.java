package com.example.projetofinal_francisco_gneto_vinicius_cssouza.entity;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * Barbearia
 */
@Entity
public class Barbearia implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    //
    @Column(nullable=false)
    private String nome;

    @Column(nullable=false)
    private String endereco;

    @Column(nullable=false)
    private String telefone;
    
    @Column(nullable = false)
    @DateTimeFormat(pattern = "HH:mm")//
    private LocalTime hAbre;
    
    @Column(nullable = false)
    @DateTimeFormat(pattern = "HH:mm")//
    private LocalTime hFecha;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name="ID_BARBEARIA")
    private List<Funcionario> funcionarios;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name="ID_BARBEARIA")
    private List<Cliente> clientes;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name="ID_BARBEARIA")
    private List<Servico> servicos;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name="ID_BARBEARIA")
    private List<Agendamento> agendamentos;

    public Barbearia() {      
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalTime gethAbre() {
        return hAbre;
    }

    public void sethAbre(LocalTime hAbre) {
        this.hAbre = hAbre;
    }

    public LocalTime gethFecha() {
        return hFecha;
    }

    public void sethFecha(LocalTime hFecha) {
        this.hFecha = hFecha;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    @Override
    public String toString() {
        return "Barbearia [endereco=" + endereco + ", id=" + id + ", nome=" + nome + ", telefone=" + telefone + "]";
    }
}
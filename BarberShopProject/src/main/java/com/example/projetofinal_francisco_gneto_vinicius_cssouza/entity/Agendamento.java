package com.example.projetofinal_francisco_gneto_vinicius_cssouza.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

import org.springframework.format.annotation.DateTimeFormat;


/**
 * Agendamento
 */
@Entity
public class Agendamento implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_BARBEARIA")
    Barbearia barbearia;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE")
    Cliente cliente;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_FUNCIONARIO")
    Funcionario funcionario;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "servico_agendamento",
        uniqueConstraints = @UniqueConstraint(columnNames = { "agendamento_id", "servico_id" }),
        joinColumns = @JoinColumn(name = "agendamento_id"),
        inverseJoinColumns = @JoinColumn(name = "servico_id"))
    private List<Servico> servicos;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    LocalDateTime horario;


    @Column(nullable = false)
    Double total;

    public Agendamento() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public void setHorario(String horario) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm");
        this.horario = LocalDateTime.parse(horario, formatter);
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    public String getHorarioComeco() {
        LocalTime data = LocalTime.MIDNIGHT;
        data = data.plusHours(horario.getHour());
        data = data.plusMinutes(horario.getMinute());
        return data.toString();
    }
    public String getHorarioTermino() {
        LocalDateTime aux = horario.plusMinutes(getTotalMinutosServicos()+5);
        LocalTime data = LocalTime.MIDNIGHT;
        data = data.plusHours(aux.getHour());
        data = data.plusMinutes(aux.getMinute());
        return data.toString();
    }
    public int getTotalMinutosServicos() {
        int qtd_mins = 0;
        for(Servico s : servicos) {
            qtd_mins += s.getDuracao();
        }
        return qtd_mins;
    }

    public Barbearia getBarbearia() {
        return barbearia;
    }

    public void setBarbearia(Barbearia barbearia) {
        this.barbearia = barbearia;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((horario == null) ? 0 : horario.hashCode());
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Agendamento other = (Agendamento) obj;
        if (horario == null) {
            if (other.horario != null)
                return false;
        } else if (!horario.equals(other.horario))
            return false;
        if (id != other.id)
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "Agendamento [cliente=" + cliente + ", funcionario=" + funcionario + ", horario=" + horario + ", id="
                + id + ", total=" + total + "]";
    }
}
package com.example.projetofinal_francisco_gneto_vinicius_cssouza.service;

import java.util.List;

import com.example.projetofinal_francisco_gneto_vinicius_cssouza.entity.Barbearia;
import com.example.projetofinal_francisco_gneto_vinicius_cssouza.repository.BarbeariaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * BarbeariaService
 */
@Service
public class BarbeariaService {

    @Autowired
    private BarbeariaRepository repository;

    @Autowired
    private AgendamentoService agendamentoService;

    public Boolean salvarBarbearia(Barbearia barbearia){

        if(repository.existsById(barbearia.getId()))
        {
            if(agendamentoService.getAgendamentosAtivosOrderByHorarios().size() > 0)
            {
                if(repository.findById(barbearia.getId()).get().gethAbre().isBefore(barbearia.gethAbre()) || repository.findById(barbearia.getId()).get().gethFecha().isAfter(barbearia.gethFecha()))
                {
                    return false;
                }
            }
           
            barbearia.setFuncionarios(repository.findAll().get(0).getFuncionarios());
            barbearia.setClientes(repository.findAll().get(0).getClientes());
            barbearia.setServicos(repository.findAll().get(0).getServicos());
            barbearia.setAgendamentos(repository.findAll().get(0).getAgendamentos());
        }
            
        if(!barbearia.gethAbre().isAfter(barbearia.gethFecha()) && !barbearia.gethAbre().equals(barbearia.gethFecha())){ 
            repository.save(barbearia);
            return true;
        }
        return false;
    }

    public List<Barbearia> getBarbearias(){
        return repository.findAll();
    }
    
    public Barbearia getBarbearia(){
        return repository.findAll().get(0);
    }

    public Barbearia getBarbeariaById (int id){
        return repository.findById(id).get();
    }

    public Page<Barbearia> findAll (int page, int n){
        return repository.findAll(PageRequest.of(page, n));
    }

    public boolean deleteBarbearia (int id){
        if(getBarbearia().getFuncionarios().isEmpty() && getBarbearia().getClientes().isEmpty() && 
            getBarbearia().getServicos().isEmpty() && agendamentoService.getAgendamentosAtivosOrderByHorarios().size() == 0)
        {
            repository.deleteById(id);
            return true;
        }
        else    
            return false;
    }

}
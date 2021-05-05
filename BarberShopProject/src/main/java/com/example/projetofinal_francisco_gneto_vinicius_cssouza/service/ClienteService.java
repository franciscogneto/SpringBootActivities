package com.example.projetofinal_francisco_gneto_vinicius_cssouza.service;

import java.util.List;

import com.example.projetofinal_francisco_gneto_vinicius_cssouza.entity.Cliente;
import com.example.projetofinal_francisco_gneto_vinicius_cssouza.repository.BarbeariaRepository;
import com.example.projetofinal_francisco_gneto_vinicius_cssouza.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository repository;

    @Autowired
    private BarbeariaRepository barbeariaRepository;

    public void salvarCliente(Cliente cliente){

        cliente.setBarbearia(barbeariaRepository.findAll().get(0));
        
        if(repository.existsById(cliente.getId()))
            cliente.setAgendamentos(repository.findById(cliente.getId()).get().getAgendamentos());
            
        repository.save(cliente);
    }

    public List<Cliente> getClientes(){
        return repository.findAll();
    }

    public Cliente getClienteById (int id){
        return repository.findById(id).get();
    }

    public Page<Cliente> findAll (int page, int n){
        return repository.findAll(PageRequest.of(page, n));
    }

    public void deleteCliente (int id){
        repository.deleteById(id);
    }
}
package com.example.projetofinal_francisco_gneto_vinicius_cssouza.service;

import java.util.List;

import com.example.projetofinal_francisco_gneto_vinicius_cssouza.entity.Funcionario;
import com.example.projetofinal_francisco_gneto_vinicius_cssouza.repository.BarbeariaRepository;
import com.example.projetofinal_francisco_gneto_vinicius_cssouza.repository.FuncionarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * FuncionarioService
 */
@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private BarbeariaRepository barbeariaRepository;

    public void salvarFuncionario(Funcionario funcionario){

        funcionario.setBarbearia(barbeariaRepository.findAll().get(0));

        if(repository.existsById(funcionario.getId()))
        {
            funcionario.setAgendamentos(repository.findById(funcionario.getId()).get().getAgendamentos());
        }

        repository.save(funcionario);
    }

    public List<Funcionario> getFuncionarios(){
        return repository.findAll();
    }

    public Funcionario getFuncionarioById (int id){
        return repository.findById(id).get();
    }

    public Page<Funcionario> findAll (int page, int n){
        return repository.findAll(PageRequest.of(page, n));
    }

    public void deleteFuncionario (int id){
        repository.deleteById(id);
    }
 
}
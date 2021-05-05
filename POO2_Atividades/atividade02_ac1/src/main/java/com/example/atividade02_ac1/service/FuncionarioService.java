package com.example.atividade02_ac1.service;

import java.util.List;

import com.example.atividade02_ac1.entity.Funcionario;
import com.example.atividade02_ac1.repository.FuncionarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    public void addFuncionario(Funcionario funcionario)
    {
        repository.save(funcionario);
    }
    
    public List<Funcionario> getFuncionarios(){
        return repository.findAll();
    }
}
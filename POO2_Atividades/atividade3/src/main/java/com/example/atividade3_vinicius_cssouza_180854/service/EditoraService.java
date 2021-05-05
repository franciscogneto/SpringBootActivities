package com.example.atividade3_vinicius_cssouza_180854.service;

import java.util.List;

import com.example.atividade3_vinicius_cssouza_180854.entity.Editora;
import com.example.atividade3_vinicius_cssouza_180854.repository.EditoraRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class EditoraService {

    @Autowired
    private EditoraRepository repository;

    public void salvarEditora(Editora editora){

        if(repository.existsById(editora.getId()))
        {
            editora.setLivros(getEditoraById(editora.getId()).getLivros());
        }

        repository.save(editora);
    }

    public List<Editora> getEditoras(){
        return repository.findAll();
    }

    public Editora getEditoraById(int id){
        return repository.findById(id).get();
    }

    public Page<Editora> findAll(int page, int n){
        return repository.findAll(PageRequest.of(page, n));
    }

    public void deleteEditora(int id){
        repository.deleteById(id);
    }
}
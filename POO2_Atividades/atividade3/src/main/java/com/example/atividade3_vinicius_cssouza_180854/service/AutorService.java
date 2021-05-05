package com.example.atividade3_vinicius_cssouza_180854.service;

import java.util.List;

import com.example.atividade3_vinicius_cssouza_180854.entity.Autor;
import com.example.atividade3_vinicius_cssouza_180854.repository.AutorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;

    public void salvarAutor(Autor autor){
        if(repository.existsById(autor.getId()))
        {
            autor.setLivros(getAutorById(autor.getId()).getLivros());
        }
        
        repository.save(autor);
    }

    public List<Autor> getAutores(){
        return repository.findAll();
    }

    public Autor getAutorById(int id){
        return repository.findById(id).get();
    }

    public Page<Autor> findAll(int page, int n){
        return repository.findAll(PageRequest.of(page, n));
    }

    public void deleteAutor(int id){
        repository.deleteById(id);
    }
}
package com.example.atividade3_vinicius_cssouza_180854.service;

import java.util.List;

import com.example.atividade3_vinicius_cssouza_180854.entity.Livro;
import com.example.atividade3_vinicius_cssouza_180854.repository.LivroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    public void salvarLivro(Livro livro){

        if(repository.existsById(livro.getId()))
        {
            livro.setAutores(getLivroById(livro.getId()).getAutores());
        }

        repository.save(livro);
    }

    public List<Livro> getLivros(){
        return repository.findAll();
    }

    public Livro getLivroById(int id){
        return repository.findById(id).get();
    }

    public Page<Livro> findAll(int page, int n){
        return repository.findAll(PageRequest.of(page, n));
    }

    public void deleteLivro(int id){
        repository.deleteById(id);
    }
}
package com.example.atividade3_francisco_godinho_neto_180141.service;

import java.util.List;

import com.example.atividade3_francisco_godinho_neto_180141.entity.Author;
import com.example.atividade3_francisco_godinho_neto_180141.repository.AuthorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository ar;

    public List<Author> getAuthors(){
        return ar.findAll();
    }

    public Author getAuthorById(int id){
        return ar.getOne(id);
    }

    public void saveAuthor(Author a){
        if(a.getEmail().trim().length() != 0 && a.getName().trim().length() != 0)
            ar.save(a);
    }
}
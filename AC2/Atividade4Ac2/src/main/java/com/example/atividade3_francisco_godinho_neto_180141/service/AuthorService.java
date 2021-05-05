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
        return ar.findById(id).get();
    }

    public void saveAuthor(Author a){
        if(a.getEmail().trim().length() != 0 && a.getName().trim().length() != 0)
        {
            if(a.getId() != 0)
            {
                Author aux = ar.findById(a.getId()).get();
                a.setBooks(aux.getBooks());
            }
            ar.save(a);
        }
    }

    public boolean removeAuthor(Author a)
    {
        if(a.getBooks() != null){
            ar.delete(a);
            return true;
        }
        return false;
    }
}
package com.example.atividade3_francisco_godinho_neto_180141.service;

import java.util.List;

import com.example.atividade3_francisco_godinho_neto_180141.entity.Book;
import com.example.atividade3_francisco_godinho_neto_180141.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    
    @Autowired
    BookRepository br;

    public List<Book> getBooks(){
        return br.findAll();
    }

    public Book getBookById(int id){
        return br.findById(id).get();
    }

    public void saveBook(Book book)
    {
        if(book.getSub_title().trim().length() != 0 && book.getTitle().trim().length() != 0)
        {
            if(book.getId() != 0)
            {
                Book aux = br.findById(book.getId()).get();
                book.setAuthors(aux.getAuthors());
            }
            br.save(book);
        }
    }

    public boolean removeBook(Book b)
    {
        if(b != null)
        {
            br.delete(b);
            return true;
        }
        return false;   
    }
}
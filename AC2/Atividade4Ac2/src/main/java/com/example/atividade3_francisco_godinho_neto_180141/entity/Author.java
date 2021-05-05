package com.example.atividade3_francisco_godinho_neto_180141.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;

@Entity
public class Author implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email;
    private String birth_date;

    @ManyToMany
    @JoinTable(
        name = "Book_Author",
        uniqueConstraints = @UniqueConstraint(columnNames = {"book_id", "author_id"}),
        joinColumns = @JoinColumn(name = "author_id"),
        inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books = new ArrayList<>();


    public Author() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getBirth_date() {
        String aux = birth_date.substring(8,10);
        aux+='/'+birth_date.substring(5, 7)+'/'+birth_date.substring(0, 4);

        return aux;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Author [id=" + id +"birth_date=" + getBirth_date() + ", email=" + email +  ", name=" + name + "]";
    }



    
}
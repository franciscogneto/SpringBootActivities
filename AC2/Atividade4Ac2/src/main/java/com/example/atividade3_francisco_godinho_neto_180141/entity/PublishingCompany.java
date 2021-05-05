package com.example.atividade3_francisco_godinho_neto_180141.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class PublishingCompany implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String adress;
    private int year_foundation;

    @OneToMany
    @JoinColumn(name = "ID_PUBLISHING_COMPANY")
    private List<Book> books;

    public PublishingCompany() {
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

    public int getYear_foundation() {
        return year_foundation;
    }

    public void setYear_foundation(int year_foundation) {
        this.year_foundation = year_foundation;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        return "PublishingCompany [adress=" + adress + ", id=" + id + ", name=" + name + ", year_foundation="
                + year_foundation + "]";
    }

}
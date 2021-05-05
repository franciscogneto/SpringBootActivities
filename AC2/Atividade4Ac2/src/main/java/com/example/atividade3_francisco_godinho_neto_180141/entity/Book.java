package com.example.atividade3_francisco_godinho_neto_180141.entity;

import java.io.Serializable;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.UniqueConstraint;

@Entity
public class Book implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String sub_title;
    private double value;
    @ManyToMany
    @JoinTable(name = "Book_Author", uniqueConstraints = @UniqueConstraint(columnNames = { "book_id",
            "author_id" }), joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

    @ManyToOne
    @JoinColumn(name = "ID_PUBLISHING_COMPANY")
    private PublishingCompany publishing_company;

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public PublishingCompany getPublishing_company() {
        return publishing_company;
    }

    public void setPublishing_company(PublishingCompany publishing_company) {
        this.publishing_company = publishing_company;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", publishing_company=" + publishing_company.getName() + ", sub_title=" + sub_title
                + ", title=" + title + ", value=" + value + "]";
    }

}
package com.example.atividade3_francisco_godinho_neto_180141.repository;

import com.example.atividade3_francisco_godinho_neto_180141.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * BookRepository
 */
public interface BookRepository extends JpaRepository<Book,Integer> {

    
}
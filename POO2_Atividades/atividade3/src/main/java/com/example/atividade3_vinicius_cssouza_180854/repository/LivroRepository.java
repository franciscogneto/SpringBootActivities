package com.example.atividade3_vinicius_cssouza_180854.repository;

import com.example.atividade3_vinicius_cssouza_180854.entity.Livro;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository <Livro, Integer> {
    
}
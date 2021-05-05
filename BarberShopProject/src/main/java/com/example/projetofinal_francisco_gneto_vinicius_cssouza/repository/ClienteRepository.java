package com.example.projetofinal_francisco_gneto_vinicius_cssouza.repository;

import com.example.projetofinal_francisco_gneto_vinicius_cssouza.entity.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
}
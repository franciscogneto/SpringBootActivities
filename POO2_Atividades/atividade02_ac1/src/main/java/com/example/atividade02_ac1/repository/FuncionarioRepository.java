package com.example.atividade02_ac1.repository;

import com.example.atividade02_ac1.entity.Funcionario;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * FuncionarioRepository
 */
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
   
}
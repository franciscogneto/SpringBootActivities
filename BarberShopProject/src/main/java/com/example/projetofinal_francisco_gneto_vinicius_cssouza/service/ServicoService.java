package com.example.projetofinal_francisco_gneto_vinicius_cssouza.service;

import java.util.List;

import com.example.projetofinal_francisco_gneto_vinicius_cssouza.entity.Servico;
import com.example.projetofinal_francisco_gneto_vinicius_cssouza.repository.BarbeariaRepository;
import com.example.projetofinal_francisco_gneto_vinicius_cssouza.repository.ServicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repository;

    @Autowired
    private BarbeariaRepository barbeariaRepository;

    public boolean salvarServico(Servico servico){

        servico.setBarbearia(barbeariaRepository.findAll().get(0));

        if(repository.existsById(servico.getId()))
        {
            servico.setAgendamentos(repository.findById(servico.getId()).get().getAgendamentos());

            if(repository.findById(servico.getId()).get().getDuracao() >= servico.getDuracao())
            {
                repository.save(servico);
                return true;
            }
            else
                return false;
        }

        repository.save(servico);
        return true;
    }

    public List<Servico> getServicos(){
        return repository.findAll();
    }

    public Servico getServicoById (int id){
        return repository.findById(id).get();
    }

    public Page<Servico> findAll (int page, int n){
        return repository.findAll(PageRequest.of(page, n));
    }

    public void deleteServico (int id){
        repository.deleteById(id);
    }
}
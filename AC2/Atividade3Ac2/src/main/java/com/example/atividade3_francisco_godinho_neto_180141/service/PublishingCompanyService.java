package com.example.atividade3_francisco_godinho_neto_180141.service;

import java.util.List;

import com.example.atividade3_francisco_godinho_neto_180141.entity.PublishingCompany;
import com.example.atividade3_francisco_godinho_neto_180141.repository.PublishingCompanyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublishingCompanyService {
    
    @Autowired
    PublishingCompanyRepository pr;

    public List<PublishingCompany> getPublishingCompanys(){
        return pr.findAll();
    }

    public PublishingCompany getPublishingCompanyById(int id){
        return pr.getOne(id);
    }

    public void savePublishingCompany(PublishingCompany pc)
    {
        if(pc.getAdress().trim().length() != 0 && pc.getName().trim().length() != 0)
            pr.save(pc);
    }
}
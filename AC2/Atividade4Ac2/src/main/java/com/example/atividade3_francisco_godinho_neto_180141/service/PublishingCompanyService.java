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
        return pr.findById(id).get();
    }

    public void savePublishingCompany(PublishingCompany pc)
    {
        if(pc.getAdress().trim().length() != 0 && pc.getName().trim().length() != 0)
        {
            if(pc.getId() != 0)
            {
                PublishingCompany aux = pr.findById(pc.getId()).get();
                pc.setBooks(aux.getBooks());
            }
            pr.save(pc);
        }
    }

    public boolean removePublishingCompany(PublishingCompany pc)
    {
        if(pc.getBooks() == null || pc.getBooks().size() == 0)
        {
            pr.delete(pc);
            return true;
        }
        return false;
    }
    
}
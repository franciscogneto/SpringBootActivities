package com.example.projetofinal_francisco_gneto_vinicius_cssouza.controller;

import com.example.projetofinal_francisco_gneto_vinicius_cssouza.service.BarbeariaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    
    @Autowired
    private BarbeariaService barbeariaService;

    @RequestMapping("/")
    public String index(Model model) {
        
        if(barbeariaService.getBarbearias().size() == 0)
        {
            model.addAttribute("verifyBarbearia", true);
        }
            
        return "index";
    }
}
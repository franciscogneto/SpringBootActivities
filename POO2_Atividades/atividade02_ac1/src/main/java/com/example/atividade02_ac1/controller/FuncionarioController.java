package com.example.atividade02_ac1.controller;

import java.util.List;

import com.example.atividade02_ac1.entity.Funcionario;
import com.example.atividade02_ac1.service.FuncionarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * FuncionarioController
 */

@Controller
@RequestMapping("/app")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @GetMapping("/cadastro")
    public ModelAndView register(){

        ModelAndView mv = new ModelAndView("registerAndFuncionariosView");

        List <Funcionario> list = service.getFuncionarios();

        mv.addObject("funcionarios", list);
        mv.addObject("verify", true);

        return mv;
    }

    @PostMapping("/cadastro")
    public ModelAndView cadastraAndListaFuncionarios(@ModelAttribute Funcionario funcionario) {

        ModelAndView mv = new ModelAndView();

        if(funcionario.getNome().trim().isEmpty() || funcionario.getCpf().trim().isEmpty() || funcionario.getDepartamento().trim().isEmpty() || funcionario.getSalario() < 0 )
        {
            List <Funcionario> list = service.getFuncionarios();

            mv.setViewName("registerAndFuncionariosView");
            
            mv.addObject("funcionarios", list);
            mv.addObject("verify", false);
            
        }
        else
        {
            service.addFuncionario(funcionario);

            mv.setViewName("redirect:/app/cadastro");
        }

        return mv;
    }
}
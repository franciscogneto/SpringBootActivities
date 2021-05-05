package com.example.projetofinal_francisco_gneto_vinicius_cssouza.controller;

import com.example.projetofinal_francisco_gneto_vinicius_cssouza.entity.Barbearia;
import com.example.projetofinal_francisco_gneto_vinicius_cssouza.service.AgendamentoService;
import com.example.projetofinal_francisco_gneto_vinicius_cssouza.service.BarbeariaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * BarbeariaController
 */
@Controller
@RequestMapping("/app")
public class BarbeariaController {

    @Autowired
    private BarbeariaService barbeariaService;

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping("/barbearia")
    public String getBarbearia(Model model, @RequestParam(defaultValue = "0") int page){

        model.addAttribute("data", barbeariaService.findAll(page, 4));

        model.addAttribute("currentPage", page);

        if(barbeariaService.getBarbearias().size() == 0)
            model.addAttribute("verifyNew", true);

        return "BarbeariaTemplate";
    }

    @PostMapping("/salvarBarbearia")
    public String salvarBarbearia(RedirectAttributes redirAttr, Barbearia barbearia){

        if(!barbearia.getNome().trim().isEmpty() && !barbearia.getEndereco().trim().isEmpty() && !barbearia.getTelefone().trim().isEmpty()){
            if(!barbeariaService.salvarBarbearia(barbearia))
                redirAttr.addFlashAttribute("verifyNew2", true);
        }
        else
            redirAttr.addFlashAttribute("verifyEdit", true);

        return "redirect:/app/barbearia";
    }

    @GetMapping("/detalhesBarbearia/{id}")
    public ModelAndView getBarbeariaDetalhes(@PathVariable(name = "id") Integer id) {
        
        Barbearia barbearia = barbeariaService.getBarbeariaById(id);
        ModelAndView mv = new ModelAndView("detalhesBarbearia");
        mv.addObject("barbearia", barbearia);
        mv.addObject("agendamentos", agendamentoService.getAgendamentosAtivosOrderByHorarios());
        mv.addObject("agendamentosHistorico", agendamentoService.getAgendamentosFinalizadosOrderByHorarios());
        mv.addObject("valorTotal", agendamentoService.getValorTotalAgendamentos());
        return mv;
    }

    @GetMapping("/findOneBarbearia")
    @ResponseBody
    public Barbearia findOneBarbearia(int id) {
        return barbeariaService.getBarbeariaById(id);
    }

    @GetMapping("/deleteBarbearia")
    public String deleteBarbearia(RedirectAttributes redirAttr, int id) {

        if(!barbeariaService.deleteBarbearia(id))
            redirAttr.addFlashAttribute("verifyDelete", true);
    
        return "redirect:/app/barbearia";
    }

}
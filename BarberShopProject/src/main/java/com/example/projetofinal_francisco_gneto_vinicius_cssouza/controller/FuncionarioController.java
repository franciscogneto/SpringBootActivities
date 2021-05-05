package com.example.projetofinal_francisco_gneto_vinicius_cssouza.controller;

import com.example.projetofinal_francisco_gneto_vinicius_cssouza.entity.Funcionario;
import com.example.projetofinal_francisco_gneto_vinicius_cssouza.service.AgendamentoService;
import com.example.projetofinal_francisco_gneto_vinicius_cssouza.service.FuncionarioService;

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
 * FuncionarioController
 */
@Controller
@RequestMapping("/app")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping("/funcionarios")
    public String getFuncionarios(Model model, @RequestParam(defaultValue = "0") int page){

        model.addAttribute("data", funcionarioService.findAll(page, 4));

        model.addAttribute("currentPage", page);

        return "FuncionarioTemplate";
    }

    @PostMapping("/salvarFuncionario")
    public String salvarFuncionario(RedirectAttributes redirAttr, Funcionario funcionario){

        if(!funcionario.getNome().trim().isEmpty() && !funcionario.getCelular().trim().isEmpty() && funcionario.getSalario() >= 0)
            funcionarioService.salvarFuncionario(funcionario);
        else
            redirAttr.addFlashAttribute("verifyEdit", true);

        return "redirect:/app/funcionarios";
    }

    @GetMapping("/detalhesFuncionario/{id}")
    public ModelAndView getFuncionarioDetalhes(@PathVariable(name = "id") Integer id) {
        
        Funcionario funcionario = funcionarioService.getFuncionarioById(id);
        ModelAndView mv = new ModelAndView("detalhesFuncionario");
        mv.addObject("funcionario", funcionario);
        mv.addObject("agendamentos", agendamentoService.getAgendamentosAtivosOrderByHorariosByFuncionario(id));
        mv.addObject("agendamentosHistorico", agendamentoService.getAgendamentosFinalizadosOrderByHorariosByFuncionario(id));

        return mv;
    }

    @GetMapping("/findOneFuncionario")
    @ResponseBody
    public Funcionario findOneFuncionario(int id) {
        return funcionarioService.getFuncionarioById(id);
    }

    @GetMapping("/deleteFuncionario")
    public String deleteFuncionario(RedirectAttributes redirAttr, int id) {

        if(funcionarioService.getFuncionarioById(id).getAgendamentos().isEmpty())
            funcionarioService.deleteFuncionario(id);
        else
            redirAttr.addFlashAttribute("verifyDelete", true);

        return "redirect:/app/funcionarios";
    }

}
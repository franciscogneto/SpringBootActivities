package com.example.projetofinal_francisco_gneto_vinicius_cssouza.controller;

import com.example.projetofinal_francisco_gneto_vinicius_cssouza.entity.Agendamento;
import com.example.projetofinal_francisco_gneto_vinicius_cssouza.service.AgendamentoService;
import com.example.projetofinal_francisco_gneto_vinicius_cssouza.service.BarbeariaService;
import com.example.projetofinal_francisco_gneto_vinicius_cssouza.service.ClienteService;
import com.example.projetofinal_francisco_gneto_vinicius_cssouza.service.FuncionarioService;
import com.example.projetofinal_francisco_gneto_vinicius_cssouza.service.ServicoService;

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
 * AgendamentoController
 */
@Controller
@RequestMapping("/app")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private ServicoService servicoService;

    @Autowired
    private BarbeariaService barbeariaService;

    @GetMapping("/agendamentos")
    public String getAgendamentos(Model model, @RequestParam(defaultValue = "0") int page){

        model.addAttribute("agendamento",  new Agendamento());
        model.addAttribute("data", agendamentoService.findAllOrderByHorarioAtivos(page, 4));

        model.addAttribute("currentPage", page);

        model.addAttribute("clientes", clienteService.getClientes());
        model.addAttribute("funcionarios", funcionarioService.getFuncionarios());
        model.addAttribute("servicos",servicoService.getServicos());


        return "agendamentosTemplate";
    }

    @GetMapping("/agendamentosHistorico")
    public String getAgendamentosHistorico(Model model, @RequestParam(defaultValue = "0") int page){

        model.addAttribute("data", agendamentoService.findAllOrderByHorariosFinalizados(page, 4));

        model.addAttribute("currentPage", page);

        return "agendamentosHistoricoTemplate";
    }

    @PostMapping("/salvarAgendamento")
    public String salvarAgendamento(RedirectAttributes redirAttr, Agendamento agendamento){
        
        if(!agendamentoService.salvarAgendamento(agendamento,barbeariaService))
            redirAttr.addFlashAttribute("verifyNew", true);
  
        return "redirect:/app/agendamentos";
    }

    @GetMapping("/detalhesAgendamento/{n}/{id}")
    public ModelAndView getAgendamentoDetalhes(@PathVariable(name = "n") Integer n, @PathVariable(name = "id") Integer id) {
        
        Agendamento agendamento = agendamentoService.getAgendamentoById(id);
        ModelAndView mv = new ModelAndView("detalhesAgendamento");
        mv.addObject("agendamento", agendamento);
        mv.addObject("template", n);
        
        return mv;
    }

    @GetMapping("/findOneAgendamento")
    @ResponseBody
    public Agendamento findOneAgendamento(int id) {
        return agendamentoService.getAgendamentoById(id);
    }

    @GetMapping("/deleteAgendamento")
    public String deleteAgendamento(RedirectAttributes redirAttr, int id) {

        agendamentoService.deleteAgendamento(id);
      
        return "redirect:/app/agendamentos";
    }

}
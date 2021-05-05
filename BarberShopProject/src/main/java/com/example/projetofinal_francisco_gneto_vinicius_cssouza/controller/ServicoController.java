package com.example.projetofinal_francisco_gneto_vinicius_cssouza.controller;

import com.example.projetofinal_francisco_gneto_vinicius_cssouza.entity.Servico;
import com.example.projetofinal_francisco_gneto_vinicius_cssouza.service.AgendamentoService;
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

@Controller
@RequestMapping("/app")
public class ServicoController {
    
    @Autowired
    private ServicoService servicoService;

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping("/servicos")
    public String getServicos(Model model, @RequestParam(defaultValue = "0") int page){

        model.addAttribute("data", servicoService.findAll(page, 4));

        model.addAttribute("currentPage", page);

        return "servicosTemplate";
    }

    @PostMapping("/salvarServico")
    public String salvarServico(RedirectAttributes redirAttr, Servico servico){

        boolean verifyEdit = false;

        if(!servico.getNome().trim().isEmpty() && servico.getPreco() > 0 && servico.getDuracao() > 0)
            verifyEdit = servicoService.salvarServico(servico);
       
        redirAttr.addFlashAttribute("verifyEdit", !verifyEdit);

        return "redirect:/app/servicos";
    }

    @GetMapping("/detalhesServico/{id}")
    public ModelAndView getServicoDetalhes(@PathVariable(name = "id") Integer id) {
        
        Servico servico = servicoService.getServicoById(id);
        ModelAndView mv = new ModelAndView("detalhesServico");
        mv.addObject("servico", servico);
        mv.addObject("agendamentos", agendamentoService.getAgendamentosAtivosOrderByHorariosByServico(id));
        mv.addObject("agendamentosHistorico", agendamentoService.getAgendamentosFinalizadosOrderByHorariosByServico(id));

        return mv;
    }

    @GetMapping("/findOneServico")
    @ResponseBody
    public Servico findOneServico(int id) {
        return servicoService.getServicoById(id);
    }

    @GetMapping("/deleteServico")
    public String deleteServico(RedirectAttributes redirAttr, int id) {

        if(servicoService.getServicoById(id).getAgendamentos().isEmpty())
            servicoService.deleteServico(id);
        else
            redirAttr.addFlashAttribute("verifyDelete", true);

        return "redirect:/app/servicos";
    }
}
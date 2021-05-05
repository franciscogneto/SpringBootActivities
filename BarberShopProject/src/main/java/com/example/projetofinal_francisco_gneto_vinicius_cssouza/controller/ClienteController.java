package com.example.projetofinal_francisco_gneto_vinicius_cssouza.controller;


import com.example.projetofinal_francisco_gneto_vinicius_cssouza.entity.Cliente;
import com.example.projetofinal_francisco_gneto_vinicius_cssouza.service.AgendamentoService;
import com.example.projetofinal_francisco_gneto_vinicius_cssouza.service.ClienteService;

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
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping("/clientes")
    public String getClientes(Model model, @RequestParam(defaultValue = "0") int page){

        model.addAttribute("data", clienteService.findAll(page, 4));

        model.addAttribute("currentPage", page);

        return "clientesTemplate";
    }

    @PostMapping("/salvarCliente")
    public String salvarCliente(RedirectAttributes redirAttr, Cliente cliente){

        if(!cliente.getNome().trim().isEmpty() && !cliente.getCelular().trim().isEmpty() && !cliente.getEmail().trim().isEmpty())
            clienteService.salvarCliente(cliente);
        else
            redirAttr.addFlashAttribute("verifyEdit", true);

        return "redirect:/app/clientes";
    }

    @GetMapping("/detalhesCliente/{id}")
    public ModelAndView getClienteDetalhes(@PathVariable(name = "id") Integer id) {
        
        Cliente cliente = clienteService.getClienteById(id);
        ModelAndView mv = new ModelAndView("detalhesCliente");
        mv.addObject("cliente", cliente);
        mv.addObject("agendamentos", agendamentoService.getAgendamentosAtivosOrderByHorariosByCliente(id));
        mv.addObject("agendamentosHistorico", agendamentoService.getAgendamentosFinalizadosOrderByHorariosByCliente(id));

        return mv;
    }

    @GetMapping("/findOneCliente")
    @ResponseBody
    public Cliente findOneCliente(int id) {
        return clienteService.getClienteById(id);
    }

    @GetMapping("/deleteCliente")
    public String deleteCliente(RedirectAttributes redirAttr, int id) {

        if(clienteService.getClienteById(id).getAgendamentos().isEmpty())
            clienteService.deleteCliente(id);
        else
            redirAttr.addFlashAttribute("verifyDelete", true);

        return "redirect:/app/clientes";
    }
}
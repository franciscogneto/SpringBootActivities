package com.example.atividade3_vinicius_cssouza_180854.controller;

import com.example.atividade3_vinicius_cssouza_180854.entity.Editora;
import com.example.atividade3_vinicius_cssouza_180854.service.EditoraService;

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
public class EditoraController {

    @Autowired
    private EditoraService editoraService;

    @GetMapping("/editoras")
    public String getEditoras(Model model, @RequestParam(defaultValue = "0") int page){

        model.addAttribute("data", editoraService.findAll(page, 4));

        model.addAttribute("currentPage", page);

        return "editorasTemplate";
    }

    @PostMapping("/salvarEditora")
    public String salvarEditora(RedirectAttributes redirAttr, Editora editora){

        if(!editora.getNome().trim().isEmpty() && !editora.getEmail().trim().isEmpty())
        {
            editoraService.salvarEditora(editora);
        }
        else
        {
            redirAttr.addFlashAttribute("verifyEdit", true);
        }
       
        return "redirect:/app/editoras";
    }

    @GetMapping("/detalhesEditora/{id}")
    public ModelAndView getEditoraDetalhes(@PathVariable(name = "id") Integer id) {
        
        Editora editora = editoraService.getEditoraById(id);
        ModelAndView mv = new ModelAndView("detalhesEditora");
        mv.addObject("editora", editora);

        return mv;
    }

    @GetMapping("/findOneEditora")
    @ResponseBody
    public Editora findOneEditora(int id) {

        return editoraService.getEditoraById(id);
    }

    @GetMapping("/deleteEditora")
    public String deleteEditora(RedirectAttributes redirAttr, int id) {

        if(editoraService.getEditoraById(id).getLivros().isEmpty())
        {
            editoraService.deleteEditora(id);
        }
        else
        {
            redirAttr.addFlashAttribute("verifyDelete", true);
        }

        return "redirect:/app/editoras";
    }

    /*@GetMapping("/editoras")
    public ModelAndView getEditoras(){
        ModelAndView mv = new ModelAndView("editorasTemplate");

        mv.addObject("editoras", editoraService.getEditoras());

        return mv;
    }

    @PostMapping("/salvarEditora")
    public String salvarEditora(@ModelAttribute Editora editora){

        editoraService.salvarEditora(editora);
      
        return "redirect:/editoras";
    }

    @GetMapping("/detalhesEditora/{id}")
    public ModelAndView getEditoraDetalhes(@PathVariable(name = "id") Integer id) {
        
        Editora editora = editoraService.getEditoraById(id);

        ModelAndView mv = new ModelAndView("detalhesEditora");

        mv.addObject("editora", editora);

        return mv;
    }*/
    
}
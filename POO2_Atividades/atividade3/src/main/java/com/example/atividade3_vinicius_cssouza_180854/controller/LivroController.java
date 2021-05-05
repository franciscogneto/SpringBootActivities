package com.example.atividade3_vinicius_cssouza_180854.controller;

import java.util.List;

import com.example.atividade3_vinicius_cssouza_180854.entity.Autor;
import com.example.atividade3_vinicius_cssouza_180854.entity.Livro;
import com.example.atividade3_vinicius_cssouza_180854.service.AutorService;
import com.example.atividade3_vinicius_cssouza_180854.service.EditoraService;
import com.example.atividade3_vinicius_cssouza_180854.service.LivroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/app")
public class LivroController {

    @Autowired
    private LivroService livroService;
    
    @Autowired
    private AutorService autorService;

    @Autowired
    private EditoraService editoraService;

    @GetMapping("/livros")
    public String getLivros(Model model, @RequestParam(defaultValue = "0") int page){

        model.addAttribute("livro",  new Livro());
        model.addAttribute("data", livroService.findAll(page, 4));
        model.addAttribute("currentPage", page);

        model.addAttribute("editoras", editoraService.getEditoras());
        model.addAttribute("autores", autorService.getAutores());
        
        return "livrosTemplate";
    }

    @PostMapping("/salvarLivro")
    public String salvarLivro(RedirectAttributes redirAttr, Livro livro){

        if(!livro.getNome().trim().isEmpty())
        {
            livroService.salvarLivro(livro);
        }
        else
        {
            redirAttr.addFlashAttribute("verifyEdit", true);
        }
      
        return "redirect:/app/livros";
    }

    @PostMapping("/associarAutorLivro")
    public String associarAutor(@ModelAttribute Autor autor, @RequestParam Integer idLivro) {
    
        Livro livro = livroService.getLivroById(idLivro);
        autor = autorService.getAutorById(autor.getId());
        
        livro.getAutores().add(autor);
        livroService.salvarLivro(livro);

        return "redirect:/app/detalhesLivro/" + idLivro;
    }

    @GetMapping("/removerAutor")
    public String removerAutor(int idLivro, int idAutor) {

        Livro livro = livroService.getLivroById(idLivro);
        Autor autor = autorService.getAutorById(idAutor);
        
        livro.getAutores().remove(autor);
        livroService.salvarLivro(livro);

        return "redirect:/app/detalhesLivro/" + idLivro;
    }

    @GetMapping("/detalhesLivro/{id}")
    public ModelAndView getLivroDetalhes(@PathVariable(name = "id") Integer id) {
        
        Livro livro = livroService.getLivroById(id);
        ModelAndView mv = new ModelAndView("detalhesLivro");
        mv.addObject("livro", livro);

        List <Autor> autoresNaoAssociados = autorService.getAutores();
        autoresNaoAssociados.removeAll(livro.getAutores());
        mv.addObject("autores", autoresNaoAssociados);

        return mv;
    }

    @GetMapping("/findOneLivro")
    @ResponseBody
    public Livro findOneLivro(int id) {

        return livroService.getLivroById(id);
    }

    @GetMapping("/deleteLivro")
    public String deleteLivro(int id) {

        livroService.deleteLivro(id);

        return "redirect:/app/livros";
    }
}
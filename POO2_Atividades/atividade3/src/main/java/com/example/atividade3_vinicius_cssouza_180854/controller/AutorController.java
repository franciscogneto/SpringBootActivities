package com.example.atividade3_vinicius_cssouza_180854.controller;

import java.util.List;

import com.example.atividade3_vinicius_cssouza_180854.entity.Autor;
import com.example.atividade3_vinicius_cssouza_180854.entity.Livro;
import com.example.atividade3_vinicius_cssouza_180854.service.AutorService;
import com.example.atividade3_vinicius_cssouza_180854.service.LivroService;

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
public class AutorController {

    @Autowired
    private AutorService autorService;

    @Autowired
    private LivroService livroService;

    @GetMapping("/autores")
    public String getAutores(Model model, @RequestParam(defaultValue = "0") int page){

        model.addAttribute("data", autorService.findAll(page, 4));

        model.addAttribute("currentPage", page);

        return "autoresTemplate";
    }

    @PostMapping("/salvarAutor")
    public String salvarAutor(RedirectAttributes redirAttr, Autor autor){

        if(!autor.getNome().trim().isEmpty() && !autor.getEmail().trim().isEmpty())
        {
            autorService.salvarAutor(autor);
        }
        else
        {
            redirAttr.addFlashAttribute("verifyEdit", true);
        }

        return "redirect:/app/autores";
    }

    /*@PostMapping("/associarAutorLivro")
    public String associarLivro(@ModelAttribute Livro livro, @RequestParam Integer idAutor) {
        

        Autor autor =  autorService.getAutorById(idAutor);
        livro = livroService.getLivroById(livro.getId());
        

        autor.getLivros().add(livro);
        autorService.salvarAutor(autor);

        return "redirect:/detalhesAutor/" + idAutor;
    }*/

    @GetMapping("/detalhesAutor/{id}")
    public ModelAndView getAutorDetalhes(@PathVariable(name = "id") Integer id) {
        
        Autor autor = autorService.getAutorById(id);
        ModelAndView mv = new ModelAndView("detalhesAutor");
        mv.addObject("autor", autor);

        List <Livro> livrosNaoAssociados = livroService.getLivros();
        livrosNaoAssociados.removeAll(autor.getLivros());
        mv.addObject("livros", livrosNaoAssociados);

        return mv;
    }

    @GetMapping("/findOneAutor")
    @ResponseBody
    public Autor findOneAutor(int id) {
        return autorService.getAutorById(id);
    }

    @GetMapping("/deleteAutor")
    public String deleteAutor(RedirectAttributes redirAttr, int id) {

        if(autorService.getAutorById(id).getLivros().isEmpty())
        {
            autorService.deleteAutor(id);
        }
        else
        {
            redirAttr.addFlashAttribute("verifyDelete", true);
        }

        return "redirect:/app/autores";
    }

    /*@GetMapping("/autores")
    public ModelAndView getAutores(){
        ModelAndView mv = new ModelAndView("autoresTemplate");

        mv.addObject("autores", autorService.getAutores());

        return mv;
    }

    @PostMapping("/salvarAutor")
    public String salvarAutor(@ModelAttribute Autor autor){

        autorService.salvarAutor(autor);
      
        return "redirect:/autores";
    }*/
}
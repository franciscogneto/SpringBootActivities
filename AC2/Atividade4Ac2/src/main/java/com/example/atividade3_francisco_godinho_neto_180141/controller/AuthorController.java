package com.example.atividade3_francisco_godinho_neto_180141.controller;

import com.example.atividade3_francisco_godinho_neto_180141.entity.Author;
import com.example.atividade3_francisco_godinho_neto_180141.service.AuthorService;
import com.example.atividade3_francisco_godinho_neto_180141.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * AuthorController
 */

@Controller
public class AuthorController {

    @Autowired
    AuthorService as;

    @Autowired
    BookService bs;

    @GetMapping("/autores")
    public ModelAndView getAuthors() {
        ModelAndView mv = new ModelAndView("AuthorTemplate");
        mv.addObject("autores", as.getAuthors());
        mv.addObject("mensagem", false);
        return mv;
    }

    @PostMapping("/salvaAutor")
    public String saveAuthor(@ModelAttribute Author author) {
        as.saveAuthor(author);
        return "redirect:/autores";
    }

    @GetMapping("/detalhesAutor/{id}")
    public ModelAndView detailsAuthor(@PathVariable(name = "id") Integer id) {
        ModelAndView mv = new ModelAndView("DetailsAuthorTemplate");
        mv.addObject("autor", as.getAuthorById(id));
        mv.addObject("livros", as.getAuthorById(id).getBooks());
        return mv;
    }

    @GetMapping("/alteraAutor")
    public ModelAndView alterAuthor(@RequestParam Integer id) {
        ModelAndView mv = new ModelAndView("AlterAuthorTemplate");
        Author a = as.getAuthorById(id);
        mv.addObject("autor", a);
        return mv;
    }

    @GetMapping("/removeAutor")
    public ModelAndView deleteAuthor(@RequestParam Integer id) {
        ModelAndView mv = new ModelAndView("AuthorTemplate");
        Author a = as.getAuthorById(id);
        boolean status = as.removeAuthor(a);
        mv.addObject("autores", as.getAuthors());
        mv.addObject("mensagem", !status);

        return mv;
    }

}
package com.example.atividade3_francisco_godinho_neto_180141.controller;

import java.util.List;

import com.example.atividade3_francisco_godinho_neto_180141.entity.Author;
import com.example.atividade3_francisco_godinho_neto_180141.entity.Book;
import com.example.atividade3_francisco_godinho_neto_180141.service.AuthorService;
import com.example.atividade3_francisco_godinho_neto_180141.service.BookService;
import com.example.atividade3_francisco_godinho_neto_180141.service.PublishingCompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
/**
 * BookController
 */

 @Controller
public class BookController {
    @Autowired
    BookService bs;

    @Autowired
    PublishingCompanyService pcs;

    @Autowired
    AuthorService as;
    
    @GetMapping("/livros")
    public ModelAndView getBooks(){
        ModelAndView mv = new ModelAndView("BookTemplate");
        mv.addObject("livro", new Book());
        mv.addObject("editoras", pcs.getPublishingCompanys());
        mv.addObject("livros", bs.getBooks());

        return mv;
    }

    @PostMapping("/salvaLivro")
    public String saveBook(@ModelAttribute Book book)
    {
        bs.saveBook(book);
        return "redirect:/livros";
    }

    @GetMapping("/detalhesLivro/{id}")
    public ModelAndView detailsBook(@PathVariable(name = "id") Integer id)
    {
        ModelAndView mv = new ModelAndView("DetailsBookTemplate");
        Book book = bs.getBookById(id);
        mv.addObject("livro", book);
        List<Author> authorsDontAssociated = as.getAuthors();
        authorsDontAssociated.removeAll(book.getAuthors());
        mv.addObject("autores", authorsDontAssociated);
        return mv;

    }

    @PostMapping("/associaLivroAutor")
    public String connectAuthor(@ModelAttribute Author autor, @RequestParam Integer codigoLivro)
    {
        autor = as.getAuthorById(autor.getId());
        Book book = bs.getBookById(codigoLivro);
        book.getAuthors().add(autor);
        bs.saveBook(book);

        return "redirect:/detalhesLivro/" + codigoLivro;
    }
    
}
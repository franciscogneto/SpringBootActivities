package com.example.atividade3_francisco_godinho_neto_180141.controller;

import com.example.atividade3_francisco_godinho_neto_180141.entity.PublishingCompany;
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
 * PublishingCompanyController
 */

@Controller
public class PublishingCompanyController {

    @Autowired
    PublishingCompanyService sv;

    @GetMapping("/editoras")
    public ModelAndView getCompanys() {
        ModelAndView mv = new ModelAndView("PublishingCompanyTemplate");
        mv.addObject("editoras", sv.getPublishingCompanys());
        mv.addObject("mensagem", false);

        return mv;
    }

    @GetMapping("/detalhesEditora/{id}")
    public ModelAndView getDetailsBook(@PathVariable(name = "id") Integer id) {
        ModelAndView mv = new ModelAndView("DetailsCompanyTemplate");
        PublishingCompany pc = sv.getPublishingCompanyById(id);
        mv.addObject("editora", pc);
        return mv;
    }

    @PostMapping("/salvaEditora")
    public String saveCompany(@ModelAttribute PublishingCompany pc) {
        sv.savePublishingCompany(pc);
        return "redirect:/editoras";
    }

    @GetMapping("/alteraEditora")
    public ModelAndView editCompany(@RequestParam Integer id) {
        ModelAndView mv = new ModelAndView("AlterCompanyTemplate");
        PublishingCompany pc = sv.getPublishingCompanyById(id);
        mv.addObject("editora", pc);
        return mv;
    }

    @GetMapping("/removeEditora")
    public ModelAndView deleteCompany(@RequestParam Integer id) {
        ModelAndView mv = new ModelAndView("PublishingCompanyTemplate");

        PublishingCompany pc = sv.getPublishingCompanyById(id);
        Boolean status = sv.removePublishingCompany(pc);
        mv.addObject("mensagem", !status);
        mv.addObject("editoras", sv.getPublishingCompanys());
        return mv;
    }
}
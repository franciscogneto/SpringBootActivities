package com.example.ac1_09032020.controller;

import java.util.HashMap;

import com.example.ac1_09032020.model.Product;
import com.example.ac1_09032020.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;


/**
 * ProductController
 */

@Controller
public class ProductController {

    @Autowired
    private ProductService ps;

    @GetMapping("/produto/{id}")
    public ModelAndView getProduct(@PathVariable("id") int id) {

        ModelAndView mv = new ModelAndView("productView");

        Product prod = ps.getProduct(id);
        
        mv.addObject("prod", prod);

        return mv;
    }
    
    @GetMapping("/produtosEmEstoque")
    public ModelAndView getProductsInStock() {

        ModelAndView mv = new ModelAndView("productsInStockView");

        HashMap<Integer, Product> prods = ps.getProductsInStock();
        
        mv.addObject("prods", prods.values());

        return mv;
    }   

    @GetMapping("/produtosValorAcima/{price}")
    public ModelAndView getProductsByPriceAbove(@PathVariable("price") double price) {

        ModelAndView mv = new ModelAndView("productsByPriceAboveView");

        HashMap<Integer, Product> prods = ps.getProductsByPriceAbove(price);
        
        mv.addObject("prods", prods.values());

        return mv;
    }

    @GetMapping("/produtosValorAbaixo/{price}")
    public ModelAndView getProductsByPriceBelow(@PathVariable("price") double price) {

        ModelAndView mv = new ModelAndView("productsByPriceBelowView");

        HashMap<Integer, Product> prods = ps.getProductsByPriceBelow(price);
        
        mv.addObject("prods", prods.values());

        return mv;
    }
}
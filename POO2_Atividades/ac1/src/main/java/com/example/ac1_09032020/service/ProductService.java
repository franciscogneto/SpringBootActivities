package com.example.ac1_09032020.service;

import java.util.HashMap;

import com.example.ac1_09032020.model.Product;
import com.example.ac1_09032020.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ProductService
 */

@Service
public class ProductService {

    @Autowired
    private ProductRepository pr;

    public Product getProduct(int id)
    {
        Product prod = pr.getProductById(id);

        return prod;
    }

    public HashMap<Integer, Product> getProductsInStock()
    {
        HashMap<Integer, Product> prods = pr.getProducts();
        HashMap<Integer, Product> prodsInStock = new HashMap<Integer, Product>();
        int i = 0;

        for (Product prod : prods.values()) {
            if(prod.getStock() > 0)
                prodsInStock.put(i++, prod);
        }

        return prodsInStock;
    }

    public HashMap<Integer, Product> getProductsByPriceAbove(double price)
    {
        HashMap<Integer, Product> prods = pr.getProducts();
        HashMap<Integer, Product> prodsAbove = new HashMap<Integer, Product>();
        int i = 0;

        for (Product prod : prods.values()) {
            if(prod.getPrice() > price)
                prodsAbove.put(i++, prod);
        }

        return prodsAbove;
    }

    public HashMap<Integer, Product> getProductsByPriceBelow(double price)
    {
        HashMap<Integer, Product> prods = pr.getProducts();
        HashMap<Integer, Product> prodsBelow = new HashMap<Integer, Product>();
        int i = 0;

        for (Product prod : prods.values()) {
            if(prod.getPrice() < price)
                prodsBelow.put(i++, prod);
        }

        return prodsBelow;
    }
}
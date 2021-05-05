package com.example.ac1_09032020.repository;

import java.util.HashMap;

import com.example.ac1_09032020.model.Product;

import org.springframework.stereotype.Repository;

/**
 * ProductRepository
 */

@Repository
public class ProductRepository {

    public HashMap<Integer, Product> prods = new HashMap<Integer, Product>();

    public ProductRepository()
    {
        prods.put(0 , new Product(0,"Notebook", 4500.0, 20));
        prods.put(1 , new Product(1,"Mouse", 250.00, 80));
        prods.put(2 , new Product(2,"Keyboard", 350.00, 40));
        prods.put(3 , new Product(3,"Mousepad", 50.00, 200));
        prods.put(4 , new Product(4,"SSD", 400.00, 50));
        prods.put(5 , new Product(5,"Monitor", 1000.00, 20));
        prods.put(6 , new Product(6,"Headset", 300.00, 30));
        prods.put(7 , new Product(7,"Headphone", 200.00, 30));
        prods.put(8 , new Product(8,"SSD M.2", 600.00, 50));
        prods.put(9 , new Product(9,"PC", 3000.00, 10));
        prods.put(10 , new Product(10,"HD 1TB", 400.00, 0));
    }

    public Product getProductById(int id)
    {
        return prods.get(id);
    }

    public HashMap<Integer, Product> getProducts()
    {
        return prods;
    }

}
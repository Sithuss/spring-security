package com.example.prepostfiltertest.controller;

import com.example.prepostfiltertest.ds.Product;
import com.example.prepostfiltertest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/sell")
    public List<Product> sellProduct() {
        List<Product> products = new ArrayList<>();
        Collections.addAll(products, new Product("Chocolate", "john"),
                new Product("Potatos", "emma"));

        return productService.sellProducts(products);
    }

    @GetMapping("/sell2")
    public List<Product> sellProduct2() {
        return productService.sellProducts2();
    }
}

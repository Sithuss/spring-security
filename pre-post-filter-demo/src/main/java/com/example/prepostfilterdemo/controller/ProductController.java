package com.example.prepostfilterdemo.controller;

import com.example.prepostfilterdemo.ds.Product;
import com.example.prepostfilterdemo.service.ProductService;
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
        List<Product>  products = new ArrayList<>();

        Collections.addAll(products, new Product("beer", "nikolai"),
                new Product("candy", "nikolai"),
                new Product("chocloate", "julien"));

        return productService.sellProduct(products);
    }

    @GetMapping("/sell2")
    private List<Product> sellProducts() {
        return productService.sellProductV2();
    }
}

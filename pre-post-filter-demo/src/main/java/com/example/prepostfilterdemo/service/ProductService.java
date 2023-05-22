package com.example.prepostfilterdemo.service;

import com.example.prepostfilterdemo.ds.Product;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductService {

    @PreFilter("filterObject.owner == authentication.name")
    public List<Product> sellProduct(List<Product> products){
        return products;
    }

    @PostFilter("filterObject.owner == authentication.name")
    public List<Product> sellProductV2() {
        List<Product>  products = new ArrayList<>();

        Collections.addAll(products, new Product("beer", "nikolai"),
                new Product("candy", "nikolai"),
                new Product("chocloate", "julien"));
        return products;
    }
}

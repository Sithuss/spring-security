package com.example.prepostfiltertest.service;

import com.example.prepostfiltertest.ds.Product;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductService {

    @PreFilter("filterObject.owner == authentication.name")
    public List<Product> sellProducts(List<Product> products) {
        return products;
    }

    @PostFilter("filterObject.owner == authentication.name")
    public List<Product> sellProducts2() {
        List<Product> products = new ArrayList<>();
        Collections.addAll(products, new Product("Chocolate", "john"),
                new Product("Potato", "emma"));
        return products;
    }
}

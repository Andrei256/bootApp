package com.example.bootApp.service;

import com.example.bootApp.model.Product;

import java.util.List;

public interface ProductService {
    void save(Product product);
    List<Product> getAll();
    Product get(Long id);
    void delete(Long id);
}

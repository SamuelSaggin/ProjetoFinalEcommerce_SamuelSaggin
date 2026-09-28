package br.edu.utfpr.pb.pw44s.server.service;

import br.edu.utfpr.pb.pw44s.server.model.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductsService {
    List<Products> findAll();
    Page<Products> findAll(Pageable pageable);
    Products findById(Long id);
    Products save(Products products);
    void deleteById(Long id);
    boolean exists(Long id);
    long count();
}

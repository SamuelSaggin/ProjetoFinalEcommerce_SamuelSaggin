package br.edu.utfpr.pb.pw44s.server.service.impl;

import br.edu.utfpr.pb.pw44s.server.model.Products;
import br.edu.utfpr.pb.pw44s.server.repository.ProductsRepository;
import br.edu.utfpr.pb.pw44s.server.service.IProductsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductsServiceImpl implements IProductsService {

    private final ProductsRepository productsRepository;

    public ProductsServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Products> findAll() {
        return this.productsRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Products> findAll(Pageable pageable) {
        return this.productsRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Products findById(Long id) {
        return this.productsRepository.findById(id).orElse(null);
    }

    @Override
    public Products save(Products products) {
        return this.productsRepository.save(products);
    }

    @Override
    public void deleteById(Long id) {
        this.productsRepository.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return this.productsRepository.existsById(id);
    }

    @Override
    public long count() {
        return this.productsRepository.count();
    }
}

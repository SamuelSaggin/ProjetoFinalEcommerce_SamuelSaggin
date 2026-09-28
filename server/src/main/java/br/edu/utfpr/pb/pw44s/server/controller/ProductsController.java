package br.edu.utfpr.pb.pw44s.server.controller;

import br.edu.utfpr.pb.pw44s.server.dto.ProductsDTO;
import br.edu.utfpr.pb.pw44s.server.mapper.ProductsMapper;
import br.edu.utfpr.pb.pw44s.server.model.Products;
import br.edu.utfpr.pb.pw44s.server.service.IProductsService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("products")
public class ProductsController {

    private final IProductsService productsService;
    private final ProductsMapper productsMapper;

    public ProductsController(IProductsService productsService, ProductsMapper productsMapper) {
        this.productsService = productsService;
        this.productsMapper = productsMapper;
    }

    @PostMapping
    public ResponseEntity<ProductsDTO> save(@Valid @RequestBody ProductsDTO productsDTO) {
        Products products = productsService.save(productsMapper.toEntity(productsDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body(
                productsMapper.toDto(products)
        );
    }

    @GetMapping
    public ResponseEntity<List<ProductsDTO>> findAll() {
        return ResponseEntity.ok(
                productsService.findAll()
                        .stream()
                        .map(productsMapper::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductsDTO> findById(@PathVariable Long id) {
        Products products = productsService.findById(id);
        if (products != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    productsMapper.toDto(products));
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productsService.deleteById(id);
    }

    @GetMapping("page")
    public ResponseEntity<Page<ProductsDTO>> findAll(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String order,
            @RequestParam(required = false) Boolean asc
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        if(order != null && asc != null) {
            pageRequest = PageRequest.of(page -1, size,
                    asc? Sort.Direction.ASC : Sort.Direction.DESC, order);
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                productsService.findAll(pageRequest).map(productsMapper::toDto)
        );

    }
}

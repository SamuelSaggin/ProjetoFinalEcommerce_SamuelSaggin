package br.edu.utfpr.pb.pw44s.server.controller;

import br.edu.utfpr.pb.pw44s.server.dto.CategoryDTO;
import br.edu.utfpr.pb.pw44s.server.mapper.CategoryMapper;
import br.edu.utfpr.pb.pw44s.server.model.Category;
import br.edu.utfpr.pb.pw44s.server.service.ICategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("categories")
public class CategoryController {

    private final ICategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(ICategoryService categoryService,  CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> save(@RequestBody CategoryDTO categoryDTO) {
        Category category = categoryService.save(categoryMapper.toEntity(categoryDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body(
                categoryMapper.toDto(category)
        );
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        return ResponseEntity.ok(
                categoryService.findAll()
                        .stream()
                        .map(categoryMapper::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        if (category != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    categoryMapper.toDto(category));
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @GetMapping("page")
    public ResponseEntity<Page<CategoryDTO>> findAll(
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
                categoryService.findAll(pageRequest).map(categoryMapper::toDto)
        );

    }
}
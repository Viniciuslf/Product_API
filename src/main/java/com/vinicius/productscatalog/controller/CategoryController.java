package com.vinicius.productscatalog.controller;

import com.vinicius.productscatalog.dto.CategoryRequestDTO;
import com.vinicius.productscatalog.dto.CategoryResponseDTO;
import com.vinicius.productscatalog.model.Category;
import com.vinicius.productscatalog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> create(@RequestBody @Valid CategoryRequestDTO request) {
        Category category = new Category(request.name());
        category = categoryRepository.save(category);
        return ResponseEntity.ok(new CategoryResponseDTO(category.getId(), category.getName()));
    }

    @GetMapping
    public List<CategoryResponseDTO> findAll() {
        return categoryRepository.findAll().stream()
                .map(cat -> new CategoryResponseDTO(cat.getId(), cat.getName()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> findById(@PathVariable String id) {
        Optional<Category> optional = categoryRepository.findById(id);
        return optional.map(cat ->
                ResponseEntity.ok(new CategoryResponseDTO(cat.getId(), cat.getName()))
        ).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> update(@PathVariable String id, @RequestBody @Valid CategoryRequestDTO request) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Category category = optional.get();
        category.setName(request.name());
        category = categoryRepository.save(category);

        return ResponseEntity.ok(new CategoryResponseDTO(category.getId(), category.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (!categoryRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categoryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

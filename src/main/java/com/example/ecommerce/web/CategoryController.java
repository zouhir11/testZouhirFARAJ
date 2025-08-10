package com.example.ecommerce.web;

import com.example.ecommerce.domain.Category;
import com.example.ecommerce.dto.CategoryRequest;
import com.example.ecommerce.repo.CategoryRepository;
import com.example.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
  private final CategoryService service;
  private final CategoryRepository repo;

  public CategoryController(CategoryService service, CategoryRepository repo) {
    this.service = service; this.repo = repo;
  }

  @PostMapping
  public Category create(@Valid @RequestBody CategoryRequest req){ return service.create(req); }

  @PutMapping("/{id}")
  public Category update(@PathVariable Long id, @Valid @RequestBody CategoryRequest req){ return service.update(id, req); }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id){ service.delete(id); }

  @GetMapping public List<Category> all(){ return repo.findAll(); }

  @PostMapping("/{categoryId}/products/{productId}")
  public Category link(@PathVariable Long categoryId, @PathVariable Long productId){
    return service.linkProduct(categoryId, productId);
  }

  @DeleteMapping("/{categoryId}/products/{productId}")
  public Category unlink(@PathVariable Long categoryId, @PathVariable Long productId){
    return service.unlinkProduct(categoryId, productId);
  }
}

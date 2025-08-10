package com.example.ecommerce.web;

import com.example.ecommerce.domain.Product;
import com.example.ecommerce.dto.ProductRequest;
import com.example.ecommerce.repo.ProductRepository;
import com.example.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
  private final ProductService service;
  private final ProductRepository repo;

  public ProductController(ProductService service, ProductRepository repo) {
    this.service = service; this.repo = repo;
  }

  @PostMapping
  public Product create(@Valid @RequestBody ProductRequest req){ return service.create(req); }

  @PutMapping("/{id}")
  public Product update(@PathVariable Long id, @Valid @RequestBody ProductRequest req){ return service.update(id, req); }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id){ service.delete(id); }

  @GetMapping public List<Product> all(){ return repo.findAll(); }
  @GetMapping("/{id}") public Product one(@PathVariable Long id){ return repo.findById(id).orElse(null); }
}

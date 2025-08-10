package com.example.ecommerce.service;

import com.example.ecommerce.domain.Product;
import com.example.ecommerce.dto.ProductRequest;
import com.example.ecommerce.error.NotFoundException;
import com.example.ecommerce.repo.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {
  private final ProductRepository products;

  public ProductService(ProductRepository products) { this.products = products; }

  public Product create(ProductRequest req){
    Product p = new Product();
    p.setName(req.name());
    p.setPrice(req.price());
    p.setStockQuantity(req.stockQuantity());
    return products.save(p);
  }

  public Product update(Long id, ProductRequest req){
    Product p = products.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
    p.setName(req.name());
    p.setPrice(req.price());
    p.setStockQuantity(req.stockQuantity());
    return p;
  }

  public void delete(Long id){
    Product p = products.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
    products.delete(p);
  }
}

package com.example.ecommerce.service;

import com.example.ecommerce.domain.Category;
import com.example.ecommerce.domain.Product;
import com.example.ecommerce.dto.CategoryRequest;
import com.example.ecommerce.error.NotFoundException;
import com.example.ecommerce.repo.CategoryRepository;
import com.example.ecommerce.repo.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryService {
  private final CategoryRepository categories;
  private final ProductRepository products;

  public CategoryService(CategoryRepository categories, ProductRepository products) {
    this.categories = categories;
    this.products = products;
  }

  public Category create(CategoryRequest req){
    Category c = new Category();
    c.setName(req.name());
    c.setDescription(req.description());
    if (req.parentId()!=null){
      Category parent = categories.findById(req.parentId())
        .orElseThrow(() -> new NotFoundException("Parent category not found"));
      c.setParent(parent);
      parent.getChildren().add(c);
    }
    return categories.save(c);
  }

  public Category update(Long id, CategoryRequest req){
    Category c = categories.findById(id).orElseThrow(() -> new NotFoundException("Category not found"));
    c.setName(req.name());
    c.setDescription(req.description());
    if (req.parentId()!=null){
      Category parent = categories.findById(req.parentId())
        .orElseThrow(() -> new NotFoundException("Parent category not found"));
      c.setParent(parent);
    } else {
      c.setParent(null);
    }
    return c;
  }

  public void delete(Long id){
    Category c = categories.findById(id).orElseThrow(() -> new NotFoundException("Category not found"));
    categories.delete(c);
  }

  public Category linkProduct(Long categoryId, Long productId){
    Category c = categories.findById(categoryId).orElseThrow(() -> new NotFoundException("Category not found"));
    Product p = products.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));
    c.getProducts().add(p);
    p.getCategories().add(c);
    return c;
  }

  public Category unlinkProduct(Long categoryId, Long productId){
    Category c = categories.findById(categoryId).orElseThrow(() -> new NotFoundException("Category not found"));
    Product p = products.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));
    c.getProducts().remove(p);
    p.getCategories().remove(c);
    return c;
  }
}

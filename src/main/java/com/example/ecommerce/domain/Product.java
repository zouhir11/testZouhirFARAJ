package com.example.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"categories"})
public class Product {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String name;

  @NotNull @Min(0)
  private BigDecimal price;

  @NotNull @Min(0)
  private Integer stockQuantity;

  @ManyToMany(mappedBy = "products")
  private Set<Category> categories = new HashSet<>();

  // getters/setters
  public Long getId() { return id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public BigDecimal getPrice() { return price; }
  public void setPrice(BigDecimal price) { this.price = price; }
  public Integer getStockQuantity() { return stockQuantity; }
  public void setStockQuantity(Integer stockQuantity) { this.stockQuantity = stockQuantity; }
  public Set<Category> getCategories() { return categories; }
}

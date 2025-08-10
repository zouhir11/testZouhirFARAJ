package com.example.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnoreProperties({"cart"})
  private List<CartItem> items = new ArrayList<>();

  public Long getId() { return id; }
  public List<CartItem> getItems() { return items; }

  @Transient
  public BigDecimal getTotal() {
    return items.stream()
      .map(i -> i.getProduct().getPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
      .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}

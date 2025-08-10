package com.example.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
public class CartItem {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false)
  @JsonIgnoreProperties({"categories"})
  private Product product;

  @ManyToOne(optional = false)
  private Cart cart;

  @Min(1)
  private int quantity;

  public Long getId() { return id; }
  public Product getProduct() { return product; }
  public void setProduct(Product product) { this.product = product; }
  public Cart getCart() { return cart; }
  public void setCart(Cart cart) { this.cart = cart; }
  public int getQuantity() { return quantity; }
  public void setQuantity(int quantity) { this.quantity = quantity; }
}

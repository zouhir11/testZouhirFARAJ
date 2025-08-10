package com.example.ecommerce.web;

import com.example.ecommerce.domain.Cart;
import com.example.ecommerce.dto.CartItemRequest;
import com.example.ecommerce.service.CartService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {
  private final CartService service;

  public CartController(CartService service) { this.service = service; }

  @PostMapping
  public Cart create(){ return service.createCart(); }

  @GetMapping("/{id}")
  public Cart get(@PathVariable Long id){ return service.getCart(id); }

  @PostMapping("/{id}/items")
  public Cart add(@PathVariable Long id, @Valid @RequestBody CartItemRequest req){
    return service.addItem(id, req);
  }

  @PutMapping("/{id}/items")
  public Cart update(@PathVariable Long id, @Valid @RequestBody CartItemRequest req){
    return service.updateItem(id, req);
  }

  @DeleteMapping("/{id}/items/{productId}")
  public Cart remove(@PathVariable Long id, @PathVariable Long productId){
    return service.removeItem(id, productId);
  }
}

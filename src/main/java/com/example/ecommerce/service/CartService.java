package com.example.ecommerce.service;

import com.example.ecommerce.domain.Cart;
import com.example.ecommerce.domain.CartItem;
import com.example.ecommerce.domain.Product;
import com.example.ecommerce.dto.CartItemRequest;
import com.example.ecommerce.error.BadRequestException;
import com.example.ecommerce.error.NotFoundException;
import com.example.ecommerce.repo.CartItemRepository;
import com.example.ecommerce.repo.CartRepository;
import com.example.ecommerce.repo.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartService {
  private final CartRepository carts;
  private final ProductRepository products;

  public CartService(CartRepository carts, ProductRepository products) {
    this.carts = carts; this.products = products;
  }

  public Cart createCart(){
    return carts.save(new Cart());
  }

  public Cart getCart(Long id){
    return carts.findById(id).orElseThrow(() -> new NotFoundException("Cart not found"));
  }

  public Cart addItem(Long cartId, CartItemRequest req){
    Cart cart = getCart(cartId);
    Product product = products.findById(req.productId())
      .orElseThrow(() -> new NotFoundException("Product not found"));
    if (req.quantity() > product.getStockQuantity())
      throw new BadRequestException("Quantity exceeds stock");

    // si l'article existe déjà, on met à jour la quantité
    var existing = cart.getItems().stream()
      .filter(i -> i.getProduct().getId().equals(product.getId())).findFirst();
    if (existing.isPresent()){
      var item = existing.get();
      item.setQuantity(req.quantity());
    } else {
      CartItem item = new CartItem();
      item.setCart(cart);
      item.setProduct(product);
      item.setQuantity(req.quantity());
      cart.getItems().add(item);
    }
    return cart;
  }

  public Cart updateItem(Long cartId, CartItemRequest req){
    return addItem(cartId, req); // même logique (remplacer quantité)
  }

  public Cart removeItem(Long cartId, Long productId){
    Cart cart = getCart(cartId);
    cart.getItems().removeIf(i -> i.getProduct().getId().equals(productId));
    return cart;
  }
}

package com.example.ecommerce.error;

public class NotFoundException extends RuntimeException {
  public NotFoundException(String msg){ super(msg); }
}

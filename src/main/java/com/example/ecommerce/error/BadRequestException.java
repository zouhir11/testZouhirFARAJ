package com.example.ecommerce.error;

public class BadRequestException extends RuntimeException {
  public BadRequestException(String msg){ super(msg); }
}

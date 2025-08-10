package com.example.ecommerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequest(
  @NotBlank String name,
  @NotNull @Min(0) BigDecimal price,
  @NotNull @Min(0) Integer stockQuantity
) {}

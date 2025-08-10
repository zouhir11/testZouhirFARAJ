package com.example.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
  @NotBlank String name,
  String description,
  Long parentId
) {}


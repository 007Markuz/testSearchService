package com.test.searchService.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
  private int id;
  private String brand;
  private String description;
  private String image;
  private double price;
  private boolean discount;
}

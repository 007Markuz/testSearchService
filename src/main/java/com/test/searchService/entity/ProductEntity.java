package com.test.searchService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "promotions")
public class Product {
  @Id
  private int id;
  private String brand;
  private String description;
  private String image;
  private double price;
  private boolean discount;
}

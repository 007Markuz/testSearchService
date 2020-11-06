package com.test.searchService.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
  public int id;
  public String brand;
  public String description;
  public String image;
  public double price;
  public int discount;
}

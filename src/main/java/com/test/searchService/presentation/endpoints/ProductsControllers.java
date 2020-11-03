package com.test.searchService.presentation.endpoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsControllers {

  @GetMapping("/products")
  public String getProducts(){
    return "products";
  }
}

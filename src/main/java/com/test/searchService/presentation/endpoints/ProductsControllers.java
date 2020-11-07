package com.test.searchService.presentation.endpoints;

import com.test.searchService.domain.service.ProductsService;
import com.test.searchService.presentation.response.ProductsResponse;
import javax.annotation.processing.SupportedOptions;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class responsible for exposing functionality to obtain products
 */
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.OPTIONS})
public class ProductsControllers {
  private ProductsService productsService;
  ProductsControllers(ProductsService productsService) {
    this.productsService = productsService;
  }


  /**
   * Endpoint used to obtain a list of products

   * @param key
   * @return ProductsResponse
   */
  @GetMapping("/products")
  public ProductsResponse getProducts(@RequestParam String key){

    return productsService.getProducts(key);
  }

}

package com.test.searchService.domain.service;

import com.test.searchService.presentation.endpoints.ProductsControllers;
import com.test.searchService.presentation.response.ProductResponse;
import com.test.searchService.presentation.response.ProductsResponse;

public class ProductsService {

  package com.test.searchService.presentation.endpoints;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.test.searchService.presentation.response.ProductResponse;
import com.test.searchService.presentation.response.ProductsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

  class ProductsControllersTest {


    private ProductsService productsService = mock(ProductsService.class);
    private ProductsControllers productsControllers=  new ProductsControllers(productsService);

    @BeforeEach
    void setUp() {

      ProductResponse productExpect = new ProductResponse();
      productExpect.id =1;
      productExpect.brand ="foo";
      productExpect.description ="dsaasd";
      productExpect.image ="dsaasd";
      productExpect.price =100;
      productExpect.discount =false;

      ProductsResponse productsResponseUne = new ProductsResponse();
      productsResponseUne.products.add(getProduct(1, "foo", 100,false));

      when(ProductsService.getProducts("foo")).thenReturn(productsResponseUne);

      ProductsResponse productsResponseTwo = new ProductsResponse();
      productsResponseTwo.products.add(getProduct(2, "dsaasd", 50.5,true));
      productsResponseTwo.products.add(getProduct(3, "dsaasd", 50.5,true));
      when(ProductsService.getProducts("dsaasd")).thenReturn(productsResponseTwo);

    }

    @Test
    void getProductsWithIncorrectBrandThemReturnEmptyList() {

      ProductsResponse productsResponse = productsControllers.getProducts("testTest");
      assertThat(productsResponse.products.isEmpty()).isTrue();
    }


    @Test
    void getProductsWithCorrectBrandThemReturnList() {

      ProductsResponse productsResponse = productsControllers.getProducts("foo");
      assertThat(productsResponse.products.isEmpty()).isFalse();
      assertThat(productsResponse.products.size()).isEqualTo(1);
      assertThat(productsResponse.products.get(0)).isEqualTo(getProduct(1, "dsaasd", 100,false));

    }

    @Test
    void getProductsWithPalindromeBrandThemReturnList() {

      ProductsResponse productsResponse = productsControllers.getProducts("dsaasd");
      assertThat(productsResponse.products.isEmpty()).isFalse();
      assertThat(productsResponse.products.size()).isEqualTo(2);
      assertThat(productsResponse.products.get(0)).isEqualTo(getProduct(2, "dsaasd", 50.5,true));
    }


    private ProductResponse getProduct(int id, String brand, double price,boolean discount){

      return ProductResponse.builder()
          .id(id)
          .brand(brand)
          .description(brand)
          .image(brand)
          .price(price)
          .discount(discount).build();
    }
  }
}

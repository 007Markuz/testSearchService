package com.test.searchService.presentation.endpoints;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.test.searchService.domain.service.ProductsServiceImpl;
import com.test.searchService.presentation.response.ProductResponse;
import com.test.searchService.presentation.response.ProductsResponse;
import java.sql.Array;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductsControllersTest {


  private ProductsServiceImpl productsService = mock(ProductsServiceImpl.class);
  private ProductsControllers productsControllers;

  @BeforeEach
  void setUp() {

    productsControllers=  new ProductsControllers(productsService);
    ProductsResponse productsResponse = new ProductsResponse();
    productsResponse.products= new ArrayList<ProductResponse>();

    when(productsService.getProducts("testTest")).thenReturn(productsResponse);

    ProductsResponse productsResponseUne = new ProductsResponse();
    productsResponseUne.products = new ArrayList<ProductResponse>();
    productsResponseUne.products.add(getProduct(1, "foo", 100,false));
    when(productsService.getProducts("foo")).thenReturn(productsResponseUne);

    ProductsResponse productsResponseTwo = new ProductsResponse();
    productsResponseTwo.products = new ArrayList<ProductResponse>();
    productsResponseTwo.products.add(getProduct(2, "dsaasd", 50.5,true));
    productsResponseTwo.products.add(getProduct(3, "dsaasd", 50.5,true));
    when(productsService.getProducts("dsaasd")).thenReturn(productsResponseTwo);

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
    assertThat(productsResponse.products.get(0)).isEqualTo(getProduct(1, "foo", 100,false));

  }

  @Test
  void getProductsWithPalindromeBrandThemReturnList() {

    ProductsResponse productsResponse = productsControllers.getProducts("foo");
    assertThat(productsResponse.products.isEmpty()).isFalse();
    assertThat(productsResponse.products.size()).isEqualTo(2);
    assertThat(productsResponse.products.get(0)).isEqualTo(getProduct(2, "dsaasd", 50.5,true));
    assertThat(productsResponse.products.get(1)).isEqualTo(getProduct(4, "dsaasd", 50.5,true));
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
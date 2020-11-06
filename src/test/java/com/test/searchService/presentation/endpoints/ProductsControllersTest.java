package com.test.searchService.presentation.endpoints;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.test.searchService.domain.service.ProductsServiceImpl;
import com.test.searchService.presentation.response.ProductResponse;
import com.test.searchService.presentation.response.ProductsResponse;
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
    productsResponseUne.products.add(getProduct(1, "foo", 100,0));
    when(productsService.getProducts("foo")).thenReturn(productsResponseUne);

    ProductsResponse productsResponseTwo = new ProductsResponse();
    productsResponseTwo.products = new ArrayList<ProductResponse>();
    productsResponseTwo.products.add(getProduct(2, "dsaasd", 50.5,50));
    productsResponseTwo.products.add(getProduct(3, "dsaasd", 50.5,50));
    when(productsService.getProducts("dsaasd")).thenReturn(productsResponseTwo);

    ProductsResponse productsResponseThree = new ProductsResponse();
    productsResponseThree.products = new ArrayList<ProductResponse>();
    productsResponseThree.products.add(getProduct(232, "dsaasd", 50.5,50));
    when(productsService.getProducts("232")).thenReturn(productsResponseThree);

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
    assertThat(productsResponse.products.get(0)).isEqualTo(getProduct(1, "foo", 100,0));

  }

  @Test
  void getProductsWithPalindromeBrandThemReturnList() {

    ProductsResponse productsResponse = productsControllers.getProducts("dsaasd");
    assertThat(productsResponse.products.isEmpty()).isFalse();
    assertThat(productsResponse.products.size()).isEqualTo(2);
    assertThat(productsResponse.products.get(0)).isEqualTo(getProduct(2, "dsaasd", 50.5,50));
    assertThat(productsResponse.products.get(1)).isEqualTo(getProduct(3, "dsaasd", 50.5,50));
  }

  @Test
  void getProductsWithPalindromeIdThemReturnList() {

    ProductsResponse productsResponse = productsControllers.getProducts("232");
    assertThat(productsResponse.products.isEmpty()).isFalse();
    assertThat(productsResponse.products.size()).isEqualTo(1);
    assertThat(productsResponse.products.get(0)).isEqualTo(getProduct(232, "dsaasd", 50.5,50));

  }


  private ProductResponse getProduct(int id, String brand, double price,int discount){

    return ProductResponse.builder()
        .id(id)
        .brand(brand)
        .description(brand)
        .image(brand)
        .price(price)
        .discount(discount)
        .build();
  }
}
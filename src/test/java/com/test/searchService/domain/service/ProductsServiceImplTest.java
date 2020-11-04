package com.test.searchService.domain.service;

import static org.junit.jupiter.api.Assertions.*;

import com.test.searchService.entity.ProductEntity;
import com.test.searchService.presentation.response.ProductResponse;
import com.test.searchService.presentation.response.ProductsResponse;
import com.test.searchService.repositories.ProductsRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductsServiceImplTest {

  @Mock
  private ProductsRepository productsRepository;

  @InjectMocks
  private ProductsServiceImpl productsControllers;

  @BeforeEach
  void setUp() {

    ProductResponse productExpect = new ProductResponse();
    productExpect.id =1;
    productExpect.brand ="foo";
    productExpect.description ="dsaasd";
    productExpect.image ="dsaasd";
    productExpect.price =100;
    productExpect.discount =false;

    List<ProductEntity> productsResponseUne = new ArrayList<ProductEntity>();
    productsResponseUne.add(getProduct(1, "foo", 100));

    when(productsRepository.fingByBrand("foo")).thenReturn(productsResponseUne);

    List<ProductEntity> productsResponseTwo = new ArrayList<ProductEntity>();
    productsResponseTwo.add(getProduct(2, "dsaasd", 101));
    productsResponseTwo.add(getProduct(3, "dsaasd", 101));

    when(productsRepository.fingByBrand("dsaasd")).thenReturn(productsResponseTwo);

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
    assertThat(productsResponse.products.get(0)).isEqualTo(
        ProductResponse
            .builder()
            .id(1)
            .brand("foo")
            .description("foo")
            .image("foo")
            .price(100)
            .discount(false)
            .build()
    );

  }

  @Test
  void getProductsWithPalindromeBrandThemReturnList() {

    ProductsResponse productsResponse = productsControllers.getProducts("dsaasd");
    assertThat(productsResponse.products.isEmpty()).isFalse();
    assertThat(productsResponse.products.size()).isEqualTo(2);
    assertThat(productsResponse.products.get(0)).isEqualTo(
        ProductResponse
            .builder()
            .id(2)
            .brand("dsaasd")
            .description("dsaasd")
            .image("dsaasd")
            .price(50.5)
            .discount(true)
            .build()
    );
  }


  private ProductEntity getProduct(int id, String brand, double price){

    return ProductEntity.builder()
        .id(id)
        .brand(brand)
        .description(brand)
        .image(brand)
        .price(price)
        .build();
  }
}
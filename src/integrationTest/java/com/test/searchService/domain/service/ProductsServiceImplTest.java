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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductsServiceImplTest {


  private ProductsRepository productsRepository = mock(ProductsRepository.class);

  private ProductsServiceImpl productsService= new ProductsServiceImpl(productsRepository,50);
  private ProductsServiceImpl productsServiceTwo= new ProductsServiceImpl(productsRepository,10);

  @BeforeEach
  void setUp() {

    List<ProductEntity> productsListUne = new ArrayList<ProductEntity>();
    productsListUne.add(getProduct(1, "foo", 100));

    when(productsRepository.findByBrandLikeOrDescriptionLike("foo","foo")).thenReturn(productsListUne);

    List<ProductEntity> productsResponseTwo = new ArrayList<ProductEntity>();
    productsResponseTwo.add(getProduct(2, "dsaasd", 101));
    productsResponseTwo.add(getProduct(3, "dsaasd", 101));

    when(productsRepository.findByBrandLikeOrDescriptionLike("dsaasd","dsaasd")).thenReturn(productsResponseTwo);

    List<ProductEntity> productsResponseThree = new ArrayList<ProductEntity>();
    productsResponseThree.add(getProduct(2, "dsaasd", 101));

    when(productsRepository.findById(2)).thenReturn(productsResponseThree);

    List<ProductEntity> productsResponseFour = new ArrayList<ProductEntity>();
    productsResponseFour.add(getProduct(232, "dsaasd", 101));

    when(productsRepository.findById(232)).thenReturn(productsResponseFour);

  }

  @Test
  void getProductsWithIncorrectBrandThemReturnEmptyList() {

    ProductsResponse productsResponse = productsService.getProducts("testTest");
    assertThat(productsResponse.products.isEmpty()).isTrue();
  }


  @Test
  void getProductsWithCorrectBrandThemReturnList() {

    ProductsResponse productsResponse = productsService.getProducts("foo");
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
            .discount(0)
            .build()
    );

  }

  @Test
  void getProductsWithPalindromeBrandThemReturnList() {

    ProductsResponse productsResponse = productsService.getProducts("dsaasd");
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
            .discount(50)
            .build()
    );
  }

  @Test
  void getProductsWithPalindromedIdThemReturnUneProduct() {

    ProductsResponse productsResponse = productsService.getProducts("2");
    assertThat(productsResponse.products.isEmpty()).isFalse();
    assertThat(productsResponse.products.size()).isEqualTo(1);
    assertThat(productsResponse.products.get(0)).isEqualTo(
        ProductResponse
            .builder()
            .id(2)
            .brand("dsaasd")
            .description("dsaasd")
            .image("dsaasd")
            .price(50.5)
            .discount(50)
            .build()
    );
  }
  @Test
  void getProductsWithPalindromeLongIdThemUneReturn() {

    ProductsResponse productsResponse = productsService.getProducts("232");
    assertThat(productsResponse.products.isEmpty()).isFalse();
    assertThat(productsResponse.products.size()).isEqualTo(1);
    assertThat(productsResponse.products.get(0)).isEqualTo(
        ProductResponse
            .builder()
            .id(232)
            .brand("dsaasd")
            .description("dsaasd")
            .image("dsaasd")
            .price(50.5)
            .discount(50)
            .build()
    );
  }

  @Test
  void getProductsWithDiscountThemUneReturn() {

    ProductsResponse productsResponse = productsServiceTwo.getProducts("232");
    assertThat(productsResponse.products.isEmpty()).isFalse();
    assertThat(productsResponse.products.size()).isEqualTo(1);
    assertThat(productsResponse.products.get(0)).isEqualTo(
        ProductResponse
            .builder()
            .id(232)
            .brand("dsaasd")
            .description("dsaasd")
            .image("dsaasd")
            .price(90.9)
            .discount(10)
            .build()
    );
  }

  @Test
  void isNumericTrue() {

    assertThat(productsService.isNumeric("1")).isTrue();
    assertThat(productsService.isNumeric("1234")).isTrue();
  }
  @Test
  void isNumericFalse() {

    assertThat(productsService.isNumeric("sabba")).isFalse();
    assertThat(productsService.isNumeric("sabba1")).isFalse();
    assertThat(productsService.isNumeric("sabba230")).isFalse();
  }

  @Test
  void isPalindromeTrue() {

    assertThat(productsService.isPalindrome("sabbas")).isTrue();
    assertThat(productsService.isPalindrome("sababas")).isTrue();
  }
  @Test
  void isPalindromeFalse() {

    assertThat(productsService.isPalindrome("sabba")).isFalse();
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
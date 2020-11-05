package com.test.searchService.domain.service;


import com.test.searchService.entity.ProductEntity;
import com.test.searchService.presentation.response.ProductResponse;
import com.test.searchService.presentation.response.ProductsResponse;
import com.test.searchService.repositories.ProductsRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;


@Service
public class ProductsServiceImpl implements ProductsService {

  private ProductsRepository productsRepository;
  private boolean switchDiscount = false;


  public ProductsServiceImpl(ProductsRepository productsRepository) {
    this.productsRepository =  productsRepository;
  }

  @Override
  public ProductsResponse getProducts(String key){
    List<ProductEntity> productList ;
    ProductsResponse productsResponse = new ProductsResponse();
    switchDiscount = isPalindrome( key);

    if(isNumeric(key)){
      int keyNumeric = Integer.parseInt(key);
      productList = productsRepository.findById(keyNumeric);
    }else{
      productList = productsRepository.findByBrand(key);
    }

    productsResponse.products = productList
        .stream()
        .map(getProductEntityProductResponseFunction())
        .collect(Collectors.toList());

    return productsResponse;
  }


  public boolean isNumeric(String key){

    if(key.replaceAll("[*a-zA-Z]", "").equals(key)){
      return true;
    }
    return false;
  }

  @Override
  public boolean isPalindrome(String key) {
    List<String> list = Arrays.asList(key.split(""));
    Collections.reverse(list);

    if(key.equals(String.join("", list))){
     return true;
    }
    return false;
  }

  private Function<ProductEntity, ProductResponse> getProductEntityProductResponseFunction() {
    return entity ->  ProductResponse
        .builder()
        .id(entity.getId())
        .brand(entity.getBrand())
        .description(entity.getDescription())
        .image(entity.getImage())
        .price(switchDiscount?entity.getPrice()/2:entity.getPrice())
        .brand(entity.getBrand())
        .discount(switchDiscount)
        .build();

  }
}

package com.test.searchService.domain.service;

import com.test.searchService.presentation.response.ProductsResponse;

/**
 *  ProductsService Interface
 */
public interface ProductsService {

  ProductsResponse getProducts(String key);

  boolean isPalindrome(String key);

  boolean isNumeric(String key);

}

package com.test.searchService.repositories;


import com.test.searchService.entity.ProductEntity;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends MongoRepository<ProductEntity, String> {

  List<ProductEntity> findAll();

  List<ProductEntity> findByBrand(String brand);

  List<ProductEntity> findById(int id);
}

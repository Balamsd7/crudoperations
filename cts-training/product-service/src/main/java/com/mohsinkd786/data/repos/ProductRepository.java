package com.mohsinkd786.data.repos;

import com.mohsinkd786.data.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,Integer> {
    // select * from Product where productId=productId
    Product findByProductId(String productId);

    // select * from Product where id > greaterThan and productId = productId
    Product findByIdIsGreaterThanAndProductId(int greaterThan,String productId);
}

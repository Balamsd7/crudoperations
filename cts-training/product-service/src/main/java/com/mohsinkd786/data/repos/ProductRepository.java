package com.mohsinkd786.data.repos;

import com.mohsinkd786.data.entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product,Integer> {
    // select * from Product where productId=productId
    Product findByProductId(String productId);

    // select * from Product where id > greaterThan and productId = productId
    Product findByIdIsGreaterThanAndProductId(int greaterThan,String productId);

    @Query("SELECT p from Product p WHERE p.price > ?1 AND p.price < ?2 ")
    List<Product> findProductsWithPriceInRange(double start, double end);

    // named parameters
   //@Query("SELECT p from Product p WHERE p.price > :start AND p.price < :end ")
   //List<Product> findProductsWithPriceInRange(@Param("start") double start,@Param("end") double end);
}

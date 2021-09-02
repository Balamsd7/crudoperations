package com.mohsinkd786.utils;

import com.mohsinkd786.data.entities.Product;
import com.mohsinkd786.dtos.ProductDto;

public class ProductMapperUtil {

    public static ProductDto toDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getProductId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setSku(product.getSku());
        return productDto;
    }

    public static Product toEntity(ProductDto productDto){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setSku(productDto.getSku());
        return product;
    }
}

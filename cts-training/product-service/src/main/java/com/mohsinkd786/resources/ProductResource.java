package com.mohsinkd786.resources;

import com.mohsinkd786.dtos.ProductDto;
import com.mohsinkd786.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductResource {

    private ProductService productService;

    @Autowired
    public ProductResource(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> findProducts(){
        return productService.findProducts();
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto){
        return productService.createProduct(productDto);
    }

    @GetMapping("{id}")
    public ProductDto findById(@PathVariable("id") String productId){
        return productService.findById(productId);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") String productId){
        return ResponseEntity.ok(productService.deleteById(productId));
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto){
        return productService.modifyProduct(productDto);
    }
}

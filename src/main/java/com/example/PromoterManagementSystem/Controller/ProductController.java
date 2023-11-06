package com.example.PromoterManagementSystem.Controller;


import com.example.PromoterManagementSystem.Model.Product;
import com.example.PromoterManagementSystem.Model.Shop;
import com.example.PromoterManagementSystem.Services.ProductServices;
import com.example.PromoterManagementSystem.Services.ShopServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductServices productServices;

    public ProductController(ProductServices productServices) {
        this.productServices = productServices;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllShop(){
        List<Product> products =  productServices.getAllProduct();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productCode}")
    public ResponseEntity<?> getShopByShopCode(@PathVariable String productCode) {
        try{
            Product products = productServices.getProductById(productCode);
            return ResponseEntity.ok(products);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        try{
            Product newProductCreated = productServices.createProduct(product);
            return ResponseEntity.ok(newProductCreated);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{productCode}")
    public ResponseEntity<?> updateProduct(@PathVariable String productCode, @RequestBody Product product) {
        try{
            Product updatedProduct = productServices.updateProduct(productCode, product);
            return ResponseEntity.ok(updatedProduct);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{productCode}")
    public ResponseEntity<?> deleteShop(@PathVariable String productCode) {
        try{
            productServices.removeShop(productCode);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

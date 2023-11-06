package com.example.PromoterManagementSystem.Services;

import com.example.PromoterManagementSystem.Model.Product;
import com.example.PromoterManagementSystem.Model.Shop;
import com.example.PromoterManagementSystem.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProductServices {
    private ProductRepository productRepository;

    @Autowired
    public ProductServices (ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Product getProductById(String productCode){
        Product product = productRepository.findById(productCode).orElse(null);
        if(product != null){
            return product;
        }else{
            throw new IllegalArgumentException("product code does not exists");
        }
    }

    public Product createProduct(Product product){
        Logger.getGlobal().log(Level.INFO, product.getProductCode());
        Product existingProduct = productRepository.findById(product.getProductCode()).orElse(null);

        if(existingProduct != null) {
            throw new IllegalArgumentException("product already exists");
        }

        return productRepository.save(product);
    }

    public Product updateProduct(String productCode, Product product){
        Product existingProduct = productRepository.findById(productCode).orElse(null);
        if(existingProduct != null){
            existingProduct.setProductType(product.getProductType());
            existingProduct.setProductTypeSpecification(product.getProductTypeSpecification());
            existingProduct.setBrandName(product.getBrandName());
            existingProduct.setStartDate(product.getStartDate());
            existingProduct.setEndDate(product.getEndDate());

            return productRepository.save(existingProduct);
        }else{
            throw new IllegalArgumentException("product code does not exist.");
        }

    }

    public void removeShop(String productCode){
        Product existingProduct = productRepository.findById(productCode).orElse(null);
        if(existingProduct != null){
            productRepository.delete(existingProduct);
        }else{
            throw new IllegalArgumentException("shop code does not exist.");
        }
    }
}

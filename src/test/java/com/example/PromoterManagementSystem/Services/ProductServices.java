package com.example.PromoterManagementSystem.Services;

import com.example.PromoterManagementSystem.Model.Product;
import com.example.PromoterManagementSystem.Repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.mockito.Mockito.*;

class ProductServicesTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServices productServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProduct_ShouldReturnAllProducts() {
        // Arrange
        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(new Product("product1", "TV", "75", "SONY", new Date(2021, 1,1), new Date(2021, 3,1)));
        expectedProducts.add(new Product("product2", "TV", "65", "SONY", new Date(2021, 1,1), new Date(2021, 3,1)));
        when(productRepository.findAll()).thenReturn(expectedProducts);

        // Act
        List<Product> actualProducts = productServices.getAllProduct();

        // Assert
        Assertions.assertEquals(expectedProducts, actualProducts);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void getProductById_ExistingProductCode_ShouldReturnProduct() {
        // Arrange
        String productCode = "Product1";
        Product expectedProduct = new Product("product1", "TV", "75", "SONY", new Date(2021, 1,1), new Date(2021, 3,1));
        when(productRepository.findById(productCode)).thenReturn(Optional.of(expectedProduct));

        // Act
        Product actualProduct = productServices.getProductById(productCode);

        // Assert
        Assertions.assertEquals(expectedProduct, actualProduct);
        verify(productRepository, times(1)).findById(productCode);
    }

    @Test
    void getProductById_NonExistingProductCode_ShouldThrowException() {
        // Arrange
        String productCode = "NonExistingProduct";
        when(productRepository.findById(productCode)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> productServices.getProductById(productCode));
        verify(productRepository, times(1)).findById(productCode);
    }

    @Test
    void createProduct_NonExistingProduct_ShouldSaveAndReturnProduct() {
        // Arrange
        Product product = new Product("product1", "TV", "75", "SONY", new Date(2021, 1,1), new Date(2021, 3,1));
        when(productRepository.findById(product.getProductCode())).thenReturn(Optional.empty());
        when(productRepository.save(product)).thenReturn(product);

        // Act
        Product createdProduct = productServices.createProduct(product);

        // Assert
        Assertions.assertEquals(product, createdProduct);
        verify(productRepository, times(1)).findById(product.getProductCode());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void createProduct_ExistingProduct_ShouldThrowException() {
        // Arrange
        Product product = new Product("product1", "TV", "75", "SONY", new Date(2021, 1,1), new Date(2021, 3,1));
        when(productRepository.findById(product.getProductCode())).thenReturn(Optional.of(product));

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> productServices.createProduct(product));
        verify(productRepository, times(1)).findById(product.getProductCode());
        verify(productRepository, never()).save(product);
    }

    @Test
    void updateProduct_ExistingProductCode_ShouldUpdateAndReturnProduct() {
        // Arrange
        String productCode = "Product1";
        Product existingProduct = new Product("product1", "TV", "75", "SONY", new Date(2021, 1,1), new Date(2021, 3,1));
        Product updatedProduct = new Product("Product1", "TV", "85", "SONY", new Date(2021, 1,1), new Date(2021, 3,1));
        updatedProduct.setProductType("New Type");
        updatedProduct.setProductTypeSpecification("New Specification");
        updatedProduct.setBrandName("New Brand");
        updatedProduct.setStartDate(new Date(2021, 2,1));
        updatedProduct.setEndDate(new Date(2021, 4,1));
        when(productRepository.findById(productCode)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(updatedProduct);

        // Act
        Product resultProduct = productServices.updateProduct(productCode, updatedProduct);

        // Assert
        Assertions.assertEquals(updatedProduct, resultProduct);
        verify(productRepository, times(1)).findById(productCode);
        verify(productRepository, times(1)).save(existingProduct);
    }

    @Test
    void updateProduct_NonExistingProductCode_ShouldThrowException() {
        // Arrange
        String productCode = "NonExistingProduct";
        Product product = new Product("product1", "TV", "75", "SONY", new Date(2021, 1,1), new Date(2021, 3,1));
        when(productRepository.findById(productCode)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> productServices.updateProduct(productCode, product));
        verify(productRepository, times(1)).findById(productCode);
        verify(productRepository, never()).save(any());
    }

    @Test
    void removeProduct_ExistingProductCode_ShouldDeleteProduct() {
        // Arrange
        String productCode = "Product1";
        Product existingProduct = new Product("Product1", "TV", "75", "SONY", new Date(2021, 1,1), new Date(2021, 3,1));
        when(productRepository.findById(productCode)).thenReturn(Optional.of(existingProduct));

        // Act
        productServices.removeProduct(productCode);

        // Assert
        verify(productRepository, times(1)).findById(productCode);
        verify(productRepository, times(1)).delete(existingProduct);
    }

    @Test
    void removeProduct_NonExistingProductCode_ShouldThrowException() {
        // Arrange
        String productCode = "NonExistingProduct";
        when(productRepository.findById(productCode)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> productServices.removeProduct(productCode));
        verify(productRepository, times(1)).findById(productCode);
        verify(productRepository, never()).delete(any());
    }
}
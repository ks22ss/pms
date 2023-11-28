package com.example.PromoterManagementSystem.Services;

import com.example.PromoterManagementSystem.Model.Shop;
import com.example.PromoterManagementSystem.Repository.ShopRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class ShopServicesTest {
    @Mock
    private ShopRepository shopRepository;

    @InjectMocks
    private ShopServices shopServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllShop_ShouldReturnAllShops() {
        // Arrange
        List<Shop> expectedShops = new ArrayList<>();
        expectedShops.add(new Shop("AB01", "ABCD", "TEER"));
        expectedShops.add(new Shop("AB02", "ABCDE", "TEER2"));
        when(shopRepository.findAll()).thenReturn(expectedShops);

        // Act
        List<Shop> actualShops = shopServices.getAllShop();

        // Assert
        Assertions.assertEquals(expectedShops, actualShops);
        verify(shopRepository, times(1)).findAll();
    }

    @Test
    void getShopById_ExistingShopCode_ShouldReturnShop() {
        // Arrange
        String shopCode = "AB01";
        Shop expectedShop = new Shop("AB01", "ABCD", "TEER");
        when(shopRepository.findById(shopCode)).thenReturn(Optional.of(expectedShop));

        // Act
        Shop actualShop = shopServices.getShopById(shopCode);

        // Assert
        Assertions.assertEquals(expectedShop, actualShop);
        verify(shopRepository, times(1)).findById(shopCode);
    }

    @Test
    void getShopById_NonExistingShopCode_ShouldThrowException() {
        // Arrange
        String shopCode = "NonExistingShop";
        when(shopRepository.findById(shopCode)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> shopServices.getShopById(shopCode));
        verify(shopRepository, times(1)).findById(shopCode);
    }

    @Test
    void createShop_NonExistingShop_ShouldSaveAndReturnShop() {
        // Arrange
        Shop shop = new Shop("AB01", "ABCD", "TEER");
        when(shopRepository.findById(shop.getShopCode())).thenReturn(Optional.empty());
        when(shopRepository.save(shop)).thenReturn(shop);

        // Act
        Shop createdShop = shopServices.createShop(shop);

        // Assert
        Assertions.assertEquals(shop, createdShop);
        verify(shopRepository, times(1)).findById(shop.getShopCode());
        verify(shopRepository, times(1)).save(shop);
    }

    @Test
    void createShop_ExistingShop_ShouldThrowException() {
        // Arrange
        Shop shop = new Shop("AB01", "ABCD", "TEER");
        when(shopRepository.findById(shop.getShopCode())).thenReturn(Optional.of(shop));

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> shopServices.createShop(shop));
        verify(shopRepository, times(1)).findById(shop.getShopCode());
        verify(shopRepository, never()).save(shop);
    }

    @Test
    void updateShop_ExistingShopCode_ShouldUpdateAndReturnShop() {
        // Arrange
        String shopCode = "Shop1";
        Shop existingShop = new Shop(shopCode, "ABCD", "TEER");
        Shop updatedShop = new Shop(shopCode, "ERFD", "WEWS");
        updatedShop.setShopAddress("New Address");
        updatedShop.setTerritory("New Territory");
        when(shopRepository.findById(shopCode)).thenReturn(Optional.of(existingShop));
        when(shopRepository.save(existingShop)).thenReturn(updatedShop);

        // Act
        Shop resultShop = shopServices.updateShop(shopCode, updatedShop);

        // Assert
        Assertions.assertEquals(updatedShop, resultShop);
        verify(shopRepository, times(1)).findById(shopCode);
        verify(shopRepository, times(1)).save(existingShop);
    }

    @Test
    void updateShop_NonExistingShopCode_ShouldThrowException() {
        // Arrange
        String shopCode = "NonExistingShop";
        Shop shop = new Shop("AB01", "ABCD", "TEER");
        when(shopRepository.findById(shopCode)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> shopServices.updateShop(shopCode, shop));
        verify(shopRepository, times(1)).findById(shopCode);
        verify(shopRepository, never()).save(any());
    }

    @Test
    void removeShop_ExistingShopCode_ShouldDeleteShop() {
        // Arrange
        String shopCode = "Shop1";
        Shop existingShop = new Shop(shopCode, "ABCD", "TEER");
        when(shopRepository.findById(shopCode)).thenReturn(Optional.of(existingShop));

        // Act
        shopServices.removeShop(shopCode);

        // Assert
        verify(shopRepository, times(1)).findById(shopCode);
        verify(shopRepository, times(1)).delete(existingShop);
    }

    @Test
    void removeShop_NonExistingShopCode_ShouldThrowException() {
        // Arrange
        String shopCode = "NonExistingShop";
        when(shopRepository.findById(shopCode)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> shopServices.removeShop(shopCode));
        verify(shopRepository, times(1)).findById(shopCode);
        verify(shopRepository, never()).delete(any());
    }
}
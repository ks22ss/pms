package com.example.PromoterManagementSystem.Services;

import com.example.PromoterManagementSystem.Model.Shop;
import com.example.PromoterManagementSystem.Repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopServices {
    private ShopRepository shopRepository;

    @Autowired
    public ShopServices(ShopRepository shopRepository){
        this.shopRepository = shopRepository;
    }

    public List<Shop> getAllShop() {
        return shopRepository.findAll();
    }

    public Shop getShopById(String shopCode){
        Shop shop = shopRepository.findById(shopCode).orElse(null);
        if(shop != null){
            return shop;
        }else{
            throw new IllegalArgumentException("shop does not exists");
        }
    }

    public Shop createShop(Shop shop){
        Shop existingShop = shopRepository.findById(shop.getShopCode()).orElse(null);
        if(existingShop != null) {
            throw new IllegalArgumentException("shop already exists");
        }
        return shopRepository.save(shop);
    }

    public Shop updateShop(String shopCode, Shop shop){
        Shop existingShop = shopRepository.findById(shopCode).orElse(null);
        if(existingShop != null){
            existingShop.setShopAddress(shop.getShopAddress());
            existingShop.setTerritory(shop.getTerritory());
            return shopRepository.save(existingShop);
        }else{
            throw new IllegalArgumentException("shop code does not exist.");
        }

    }

    public void removeShop(String shopCode){
        Shop existingShop = shopRepository.findById(shopCode).orElse(null);
        if(existingShop != null){
            shopRepository.delete(existingShop);
        }else{
            throw new IllegalArgumentException("shop code does not exist.");
        }
    }
}

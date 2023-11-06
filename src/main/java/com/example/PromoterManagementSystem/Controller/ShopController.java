package com.example.PromoterManagementSystem.Controller;

import com.example.PromoterManagementSystem.Model.Shop;
import com.example.PromoterManagementSystem.Services.ShopServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {
    private ShopServices shopServices;

    public ShopController(ShopServices shopServices) {
        this.shopServices = shopServices;
    }

    @GetMapping
    public ResponseEntity<List<Shop>> getAllShop(){
        List<Shop> shops =  shopServices.getAllShop();
        return ResponseEntity.ok(shops);
    }

    @GetMapping("/{shopCode}")
    public ResponseEntity<?> getShopByShopCode(@PathVariable String shopCode) {
        try{
            Shop shop = shopServices.getShopById(shopCode);
            return ResponseEntity.ok(shop);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createShop(@RequestBody Shop shop) {
        try{
            Shop newShopCreated = shopServices.createShop(shop);
            return ResponseEntity.ok(newShopCreated);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{shopCode}")
    public ResponseEntity<?> updateShop(@PathVariable String shopCode, @RequestBody Shop shop) {
        try{
            Shop updatedShop = shopServices.updateShop(shopCode, shop);
            return ResponseEntity.ok(updatedShop);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{shopCode}")
    public ResponseEntity<?> deleteShop(@PathVariable String shopCode) {
        try{
            shopServices.removeShop(shopCode);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}

package com.example.PromoterManagementSystem.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema = "pms", name = "shop")
public class Shop {

    @Id
    private String shopCode;

    private String shopAddress;
    private String territory;

    public Shop() {

    }
    public Shop(String shopCode, String shopAddress, String territory) {
        this.shopCode = shopCode;
        this.shopAddress = shopAddress;
        this.territory = territory;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }
}

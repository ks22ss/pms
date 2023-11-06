package com.example.PromoterManagementSystem.Dto;

public class OrderCreateDTO {
    private String productCode;
    private String userName;

    private String shopCode;

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    private Integer qty;

    public OrderCreateDTO(String productCode, String userName, String shopCode, Integer qty) {
        this.productCode = productCode;
        this.userName = userName;
        this.shopCode = shopCode;
        this.qty = qty;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }
}

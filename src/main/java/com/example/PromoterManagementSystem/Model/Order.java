package com.example.PromoterManagementSystem.Model;


import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(schema = "pms", name = "order")
public class Order {

    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_code")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "shop_code")
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Date orderDate = Date.valueOf(LocalDate.now());



    private Integer qty;

    public Order() {

    }

    public Order(Product product, Shop shop, User user, Integer qty) {
        this.product = product;
        this.shop = shop;
        this.user = user;
        this.qty = qty;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
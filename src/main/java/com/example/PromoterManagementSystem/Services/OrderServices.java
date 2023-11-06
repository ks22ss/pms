package com.example.PromoterManagementSystem.Services;

import com.example.PromoterManagementSystem.Dto.OrderCreateDTO;
import com.example.PromoterManagementSystem.Model.Order;
import com.example.PromoterManagementSystem.Model.Product;
import com.example.PromoterManagementSystem.Model.Shop;
import com.example.PromoterManagementSystem.Model.User;
import com.example.PromoterManagementSystem.Repository.OrderRepository;
import com.example.PromoterManagementSystem.Repository.ProductRepository;
import com.example.PromoterManagementSystem.Repository.ShopRepository;
import com.example.PromoterManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServices {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private ShopRepository shopRepository;
    private UserRepository userRepository;

    @Autowired
    public OrderServices(OrderRepository orderRepository, ProductRepository productRepository, ShopRepository shopRepository, UserRepository userRepository){
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.shopRepository = shopRepository;
        this.userRepository = userRepository;
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order getOrderById(Long orderId){
        Order order = orderRepository.findById(orderId).orElse(null);
        if(order != null){
            return order;
        }else{
            throw new IllegalArgumentException("order does not exists");
        }
    }

    public List<Order> getOrderByUsername(String userName){
        User user = userRepository.findByUsername(userName);
        if(user == null) {
            throw new IllegalArgumentException("user does not exist");
        }
        return orderRepository.findByUserId(user.getId());
    }

    public Order createOrder(OrderCreateDTO orderCreateDTO) {

        Product product = productRepository.findById(orderCreateDTO.getProductCode()).orElse(null);
        Shop shop = shopRepository.findById(orderCreateDTO.getShopCode()).orElse(null);
        User user = userRepository.findByUsername(orderCreateDTO.getUserName());

        if(product == null){
            throw new IllegalArgumentException("product does not exist.");
        }
        if(shop == null){
            throw new IllegalArgumentException("shop does not exist.");
        }
        if(user == null){
            throw new IllegalArgumentException("user does not exist.");
        }

        Order order = new Order();
        order.setProduct(product);
        order.setShop(shop);
        order.setUser(user);
        order.setQty(orderCreateDTO.getQty());

        return orderRepository.save(order);
    }

    public Order updateOrder(Long orderId, OrderCreateDTO orderCreateDTO) {

        Order order = orderRepository.findById(orderId).orElse(null);
        Product product = productRepository.findById(orderCreateDTO.getProductCode()).orElse(null);
        Shop shop = shopRepository.findById(orderCreateDTO.getShopCode()).orElse(null);
        User user = userRepository.findByUsername(orderCreateDTO.getUserName());


        if(order == null){
            throw new IllegalArgumentException("order does not exist.");
        }
        if(product == null){
            throw new IllegalArgumentException("product does not exist.");
        }
        if(shop == null){
            throw new IllegalArgumentException("shop does not exist.");
        }
        if(user == null){
            throw new IllegalArgumentException("user does not exist.");
        }

        order.setProduct(product);
        order.setShop(shop);
        order.setUser(user);
        order.setQty(orderCreateDTO.getQty());

        return orderRepository.save(order);
    }

    public void deleteOrder(Long orderId){
        Order existingOrder = orderRepository.findById(orderId).orElse(null);
        if(existingOrder != null){
            orderRepository.delete(existingOrder);
        }else{
            throw new IllegalArgumentException("order does not exist.");
        }
    }

}

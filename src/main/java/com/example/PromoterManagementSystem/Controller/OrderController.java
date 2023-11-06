package com.example.PromoterManagementSystem.Controller;

import com.example.PromoterManagementSystem.Dto.OrderCreateDTO;
import com.example.PromoterManagementSystem.Model.Order;
import com.example.PromoterManagementSystem.Services.OrderServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderServices orderServices;
    public OrderController(OrderServices orderServices){
        this.orderServices = orderServices;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrder() {
        return ResponseEntity.ok(orderServices.getAllOrders());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable Long orderId) {
        try {
            return ResponseEntity.ok(orderServices.getOrderById(orderId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity<?> getOrderByUserName(@PathVariable String userName) {
        try {
            return ResponseEntity.ok(orderServices.getOrderByUsername(userName));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderCreateDTO orderCreateDTO){
        try {
            return ResponseEntity.ok(orderServices.createOrder(orderCreateDTO));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<?> updateOrder( @PathVariable Long orderId ,@RequestBody OrderCreateDTO orderCreateDTO){
        try {
            return ResponseEntity.ok(orderServices.updateOrder(orderId, orderCreateDTO));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId){
        try {
            orderServices.deleteOrder(orderId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}

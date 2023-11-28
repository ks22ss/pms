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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServicesTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ShopRepository shopRepository;

    @Mock
    private UserRepository userRepository;

    private OrderServices orderServices;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        orderServices = new OrderServices(orderRepository, productRepository, shopRepository, userRepository);
    }

    @Test
    public void testGetAllOrders() {
        List<Order> mockOrders = new ArrayList<>();
        mockOrders.add(new Order());
        mockOrders.add(new Order());

        when(orderRepository.findAll()).thenReturn(mockOrders);

        List<Order> result = orderServices.getAllOrders();

        assertEquals(mockOrders.size(), result.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    public void testGetOrderById() {
        Long orderId = 1L;
        Order mockOrder = new Order();

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(mockOrder));

        Order result = orderServices.getOrderById(orderId);

        assertNotNull(result);
        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    public void testGetOrderByUsername() {
        String userName = "exampleUser";
        User mockUser = new User();

        when(userRepository.findByUsername(userName)).thenReturn(mockUser);

        List<Order> mockOrders = new ArrayList<>();
        mockOrders.add(new Order());
        mockOrders.add(new Order());

        when(orderRepository.findByUserId(mockUser.getId())).thenReturn(mockOrders);

        List<Order> result = orderServices.getOrderByUsername(userName);

        assertEquals(mockOrders.size(), result.size());
        verify(userRepository, times(1)).findByUsername(userName);
        verify(orderRepository, times(1)).findByUserId(mockUser.getId());
    }

    @Test
    public void testCreateOrder() {
        OrderCreateDTO orderCreateDTO = new OrderCreateDTO("P123", "S345", "exampleUser", 2);

        Product mockProduct = new Product();
        Shop mockShop = new Shop();
        User mockUser = new User();
        Order mockOrder = new Order();

        when(productRepository.findById(orderCreateDTO.getProductCode())).thenReturn(Optional.of(mockProduct));
        when(shopRepository.findById(orderCreateDTO.getShopCode())).thenReturn(Optional.of(mockShop));
        when(userRepository.findByUsername(orderCreateDTO.getUserName())).thenReturn(mockUser);
        when(orderRepository.save(any(Order.class))).thenReturn(mockOrder);

        Order result = orderServices.createOrder(orderCreateDTO);

        assertNotNull(result);
        verify(productRepository, times(1)).findById(orderCreateDTO.getProductCode());
        verify(shopRepository, times(1)).findById(orderCreateDTO.getShopCode());
        verify(userRepository, times(1)).findByUsername(orderCreateDTO.getUserName());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    public void testUpdateOrder() {
        Long orderId = 1L;
        OrderCreateDTO orderCreateDTO = new OrderCreateDTO("P123", "S345", "exampleUser", 2);

        Order mockOrder = new Order();
        Product mockProduct = new Product();
        Shop mockShop = new Shop();
        User mockUser = new User();

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(mockOrder));
        when(productRepository.findById(orderCreateDTO.getProductCode())).thenReturn(Optional.of(mockProduct));
        when(shopRepository.findById(orderCreateDTO.getShopCode())).thenReturn(Optional.of(mockShop));
        when(userRepository.findByUsername(orderCreateDTO.getUserName())).thenReturn(mockUser);
        when(orderRepository.save(any(Order.class))).thenReturn(mockOrder);

        Order result = orderServices.updateOrder(orderId, orderCreateDTO);

        assertNotNull(result);
        verify(orderRepository, times(1)).findById(orderId);
        verify(productRepository, times(1)).findById(orderCreateDTO.getProductCode());
        verify(shopRepository, times(1)).findById(orderCreateDTO.getShopCode());
        verify(userRepository, times(1)).findByUsername(orderCreateDTO.getUserName());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    public void testDeleteOrder() {
        Long orderId = 1L;
        Order mockOrder = new Order();

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(mockOrder));

        orderServices.deleteOrder(orderId);

        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, times(1)).delete(mockOrder);
    }
}
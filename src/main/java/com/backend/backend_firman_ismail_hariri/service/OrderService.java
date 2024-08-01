package com.backend.backend_firman_ismail_hariri.service;

import com.backend.backend_firman_ismail_hariri.model.entity.Order;
import com.backend.backend_firman_ismail_hariri.model.entity.Product;
import com.backend.backend_firman_ismail_hariri.model.entity.User;
import com.backend.backend_firman_ismail_hariri.repository.OrderRepository;
import com.backend.backend_firman_ismail_hariri.repository.ProductRepository;
import com.backend.backend_firman_ismail_hariri.repository.UserRepository;
import com.backend.backend_firman_ismail_hariri.security.AuthenticatedUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticatedUserUtil authenticatedUserUtil;

    public Order createOrder(Order order) {
        User customer = authenticatedUserUtil.getAuthenticatedUser();

        if (customer == null || !"CUSTOMER".equals(customer.getRole())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Unauthorized: Only customers can create orders");
        }

        // Validate the product
        if (order.getProduct() == null || order.getProduct().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product must be provided");
        }

        Product product = productRepository.findById(order.getProduct().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        // Calculate the total price based on product price and quantity
        double productPrice = product.getPrice();
        double totalPrice = productPrice * order.getQuantity();
        order.setTotalPrice(totalPrice);

        // Apply discounts and free shipping
        if (totalPrice > 50000) {
            order.setTotalPrice(totalPrice * 0.9); // 10% discount
        }
        if (totalPrice > 15000) {
            order.setFreeShipping(true);
        }

        order.setCustomer(customer);
        order.setProduct(product);

        return orderRepository.save(order);
    }

    public List<Order> listOrdersByCustomer(User customer) {
        if (customer.getId() == null) {
            throw new IllegalArgumentException("Customer ID cannot be null");
        }
        return orderRepository.findByCustomer(customer);
    }

    public List<Order> listOrdersByMerchant(User merchant) {
        if (merchant.getId() == null) {
            throw new IllegalArgumentException("Merchant ID cannot be null");
        }
        return orderRepository.findByProduct_Merchant(merchant);
    }
}


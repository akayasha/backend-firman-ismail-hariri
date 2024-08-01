package com.backend.backend_firman_ismail_hariri.service;

import com.backend.backend_firman_ismail_hariri.model.entity.Order;
import com.backend.backend_firman_ismail_hariri.model.entity.User;
import com.backend.backend_firman_ismail_hariri.repository.OrderRepository;
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
    private UserRepository userRepository;

    @Autowired
    private AuthenticatedUserUtil authenticatedUserUtil;

    public Order createOrder(Order order, User customer) {
        User merchant = authenticatedUserUtil.getAuthenticatedUser();

        if (merchant == null || !"CUSTOMER".equals(merchant.getRole())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Unauthorized: Only merchants can create products");
        }

        order.setCustomer(merchant);
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

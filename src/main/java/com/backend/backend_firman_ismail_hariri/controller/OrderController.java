package com.backend.backend_firman_ismail_hariri.controller;

import com.backend.backend_firman_ismail_hariri.model.entity.Order;
import com.backend.backend_firman_ismail_hariri.model.entity.User;
import com.backend.backend_firman_ismail_hariri.model.responseHandler.ResponseHandler;
import com.backend.backend_firman_ismail_hariri.security.AuthenticatedUserUtil;
import com.backend.backend_firman_ismail_hariri.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AuthenticatedUserUtil authenticatedUserUtil;

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody Order order, Authentication authentication) {
        try {

            User authenticatedUser = authenticatedUserUtil.getAuthenticatedUser();

            if (authenticatedUser == null) {
                return ResponseHandler.generateResponse("User not authenticated", HttpStatus.UNAUTHORIZED, null);
            }


            if (!"CUSTOMER".equals(authenticatedUser.getRole())) {
                return ResponseHandler.generateResponse("Access denied: Only customers can create orders", HttpStatus.FORBIDDEN, null);
            }


            Order createdOrder = orderService.createOrder(order, authenticatedUser);
            return ResponseHandler.successResponse(createdOrder);
        } catch (IllegalArgumentException ex) {

            return ResponseHandler.badRequestResponse(ex.getMessage());
        } catch (ResponseStatusException ex) {

            return ResponseHandler.generateResponse(ex.getReason(), ex.getStatus(), null);
        } catch (Exception ex) {

            return ResponseHandler.generateResponse("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/by-customer")
    public ResponseEntity<?> listOrdersByCustomer(Authentication authentication) {
        try {

            User authenticatedUser = authenticatedUserUtil.getAuthenticatedUser();

            if (authenticatedUser == null) {
                return ResponseHandler.generateResponse("User not authenticated", HttpStatus.UNAUTHORIZED, null);
            }


            List<Order> orders = orderService.listOrdersByCustomer(authenticatedUser);
            return ResponseHandler.successResponse(orders);
        } catch (IllegalArgumentException ex) {

            return ResponseHandler.badRequestResponse(ex.getMessage());
        } catch (Exception ex) {

            return ResponseHandler.generateResponse("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/by-merchant")
    public ResponseEntity<?> listOrdersByMerchant(Authentication authentication) {
        try {
            User authenticatedUser = authenticatedUserUtil.getAuthenticatedUser();

            if (authenticatedUser == null) {
                return ResponseHandler.generateResponse("User not authenticated", HttpStatus.UNAUTHORIZED, null);
            }


            List<Order> orders = orderService.listOrdersByMerchant(authenticatedUser);
            return ResponseHandler.successResponse(orders);
        } catch (IllegalArgumentException ex) {

            return ResponseHandler.badRequestResponse(ex.getMessage());
        } catch (Exception ex) {

            return ResponseHandler.generateResponse("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}

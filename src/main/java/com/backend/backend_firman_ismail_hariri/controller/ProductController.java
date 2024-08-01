package com.backend.backend_firman_ismail_hariri.controller;

import com.backend.backend_firman_ismail_hariri.model.entity.Product;
import com.backend.backend_firman_ismail_hariri.model.entity.User;
import com.backend.backend_firman_ismail_hariri.model.responseHandler.ResponseHandler;
import com.backend.backend_firman_ismail_hariri.security.AuthenticatedUserUtil;
import com.backend.backend_firman_ismail_hariri.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private AuthenticatedUserUtil authenticatedUserUtil;

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody Product product, Authentication authentication) {
        try {
            User merchant = authenticatedUserUtil.getAuthenticatedUser();
            if (merchant == null || !"MERCHANT".equals(merchant.getRole())) {
                return ResponseHandler.generateResponse("Unauthorized: Only merchants can create products", HttpStatus.FORBIDDEN, null);
            }
            Product createdProduct = productService.createProduct(product);
            return ResponseHandler.successResponse(createdProduct);
        } catch (ResponseStatusException ex) {
            return ResponseHandler.generateResponse(ex.getReason(), ex.getStatus(), null);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody Product product, Authentication authentication) {
        try {
            User user = authenticatedUserUtil.getAuthenticatedUser();
            if (user == null || !"MERCHANT".equals(user.getRole())) {
                return ResponseHandler.generateResponse("Unauthorized: Only merchants can update products", HttpStatus.FORBIDDEN, null);
            }
            Product updatedProduct = productService.updateProduct(product);
            return ResponseHandler.successResponse(updatedProduct);
        } catch (ResponseStatusException ex) {
            return ResponseHandler.generateResponse(ex.getReason(), ex.getStatus(), null);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> listProducts() {
        try {
            List<Product> products = productService.listProducts();
            return ResponseHandler.successResponse(products);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/by-merchant")
    public ResponseEntity<?> listProductsByMerchant(Authentication authentication) {
        try {
            User merchant = authenticatedUserUtil.getAuthenticatedUser();
            if (merchant == null || !"MERCHANT".equals(merchant.getRole())) {
                return ResponseHandler.generateResponse("Unauthorized: Only merchants can list products", HttpStatus.FORBIDDEN, null);
            }
            List<Product> products = productService.listProductsByMerchant();
            return ResponseHandler.successResponse(products);
        } catch (ResponseStatusException ex) {
            return ResponseHandler.generateResponse(ex.getReason(), ex.getStatus(), null);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId, Authentication authentication) {
        try {
            User merchant = authenticatedUserUtil.getAuthenticatedUser();
            if (merchant == null || !"MERCHANT".equals(merchant.getRole())) {
                return ResponseHandler.generateResponse("Unauthorized: Only merchants can delete products", HttpStatus.FORBIDDEN, null);
            }
            if (productId == null) {
                return ResponseHandler.generateResponse("Product ID must not be null", HttpStatus.BAD_REQUEST, null);
            }
            productService.deleteProduct(productId);
            return ResponseHandler.successResponse("Product deleted successfully");
        } catch (ResponseStatusException ex) {
            return ResponseHandler.generateResponse(ex.getReason(), ex.getStatus(), null);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}

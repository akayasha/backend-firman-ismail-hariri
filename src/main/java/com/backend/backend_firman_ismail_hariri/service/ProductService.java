package com.backend.backend_firman_ismail_hariri.service;

import com.backend.backend_firman_ismail_hariri.model.entity.Product;
import com.backend.backend_firman_ismail_hariri.model.entity.User;
import com.backend.backend_firman_ismail_hariri.repository.ProductRepository;
import com.backend.backend_firman_ismail_hariri.repository.UserRepository;
import com.backend.backend_firman_ismail_hariri.security.AuthenticatedUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticatedUserUtil authenticatedUserUtil;

    public Product createProduct(Product product) {
        User merchant = authenticatedUserUtil.getAuthenticatedUser();
        if (merchant == null || !"MERCHANT".equals(merchant.getRole())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Unauthorized: Only merchants can create products");
        }
        product.setMerchant(merchant);
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        User user = authenticatedUserUtil.getAuthenticatedUser();
        if (!"MERCHANT".equals(user.getRole())) {
            throw new RuntimeException("Unauthorized: Only merchants can update products");
        }
        product.setMerchant(user);
        return productRepository.save(product);
    }


    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    public List<Product> listProductsByMerchant() {
        User merchant = authenticatedUserUtil.getAuthenticatedUser();
        return productRepository.findByMerchant(merchant);
    }

    public void deleteProduct(Long productId) {
        User merchant = authenticatedUserUtil.getAuthenticatedUser();
        if (productId == null) {
            throw new RuntimeException("Product ID must not be null");
        } else if (merchant == null || !"MERCHANT".equals(merchant.getRole())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Unauthorized: Only merchants can create products");
        }
        productRepository.deleteById(productId);
    }
}

